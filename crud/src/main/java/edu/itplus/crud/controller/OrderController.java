package edu.itplus.crud.controller;


import edu.itplus.crud.domain.Customer;
import edu.itplus.crud.domain.Order;
import edu.itplus.crud.domain.OrderDetail;
import edu.itplus.crud.domain.Product;
import edu.itplus.crud.model.CustomerDto;
import edu.itplus.crud.model.OrderDetailDto;
import edu.itplus.crud.model.OrderDto;
import edu.itplus.crud.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/orders")
public class OrderController {
   @Autowired
    OrderService orderService;

   @Autowired
    OrderDetailService orderDetailService;

   @Autowired
   CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    StorageService storageService;
//    @GetMapping("add")
//    public String add(Model model) {
//        ProductDto dto = new ProductDto();
//
//        dto.setIsEdit(false);
//        model.addAttribute("product", dto);
//        return "products/addOrEdit";
//    }

    @GetMapping("edit/{orderId}")
    public ModelAndView edit(ModelMap model, @PathVariable("orderId") Long orderId) {
        Optional<Order> opt = orderService.findById(orderId);
        OrderDto dto = new OrderDto();
        if (opt.isPresent()) {

            Order entity = opt.get();
            BeanUtils.copyProperties(entity, dto);

            Optional<Customer> cus = customerService.findById(opt.get().getCustomerId());
            dto.setCustomerName(cus.get().getName());
            if (dto.getAddress().isEmpty()){
                dto.setAddress(cus.get().getAddress());
            }

            dto.setCustomerEmail(cus.get().getEmail());

           List<OrderDetailDto> orderDetailsList = orderDetailService.findByOrderId(opt.get().getOrderId()).stream().map(orderDetail -> {
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                BeanUtils.copyProperties(orderDetail, orderDetailDto);
               Optional<Product> pro = productService.findById(orderDetailDto.getProductId());
               orderDetailDto.setProductName(pro.get().getName());
               orderDetailDto.setImage(pro.get().getImage());
               orderDetailDto.setUnitPrice(pro.get().getUnitPrice());
                return orderDetailDto;
            }).toList();

            model.addAttribute("order", dto);
            model.addAttribute("orderDetails", orderDetailsList);

            return new ModelAndView("orders/addOrEdit", model);
        }
        model.addAttribute("message", "model is not existed");
        return new ModelAndView("forward: /admin/orders", model);
    }


    @ModelAttribute("customers")
    public List<CustomerDto> getCustomerDto() {
        return customerService.findAll().stream().map(item -> {
            CustomerDto dto = new CustomerDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }

    @GetMapping("delete/{orderId}")
    public ModelAndView delete(ModelMap model, @PathVariable("orderId") Long orderId) throws IOException {
        Optional<Order> opt = orderService.findById(orderId);

        if (opt.isPresent()){

            orderService.delete(opt.get());

            model.addAttribute("message","Order is deleted");
        }
        else {
            model.addAttribute("message","Order is not found");

        }
        return new ModelAndView("forward:/admin/orders");
    }
    @GetMapping("deleteOrderDetail/{orderDetailId}")
    public ModelAndView deleteOrderDetail(ModelMap model, @PathVariable("orderDetailId") Long orderDetailId) throws IOException {
        Optional<OrderDetail> opt = orderDetailService.findById(orderDetailId);

        if (opt.isPresent()){

            orderDetailService.delete(opt.get());

            model.addAttribute("message","Orderdetail is deleted");
        }
        else {
            model.addAttribute("message","Orderdetail is not found");

        }

        return new ModelAndView("forward:/admin/orders");
    }
    @PostMapping("save")
    public ModelAndView save(ModelMap model, @ModelAttribute("order")
    OrderDto dto, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return new ModelAndView("orders/addOrEdit");
        }
        Order entity = new Order();
        BeanUtils.copyProperties(dto, entity);
//        entity.setStatus(dto.getStatus());
        orderService.save(entity);
        model.addAttribute("message", "Order ís saved");

        return new ModelAndView("forward:/admin/orders", model);
    }
        @GetMapping("/images/{filename:.+}")
        @ResponseBody
        public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
            Resource file = storageService.loadAsResource(filename);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Order> list = orderService.findAll();
        model.addAttribute("orders", list);
        return "orders/list";
    }

@GetMapping("search")
    public String search(ModelMap model, @RequestParam(value = "id", required = false) Long id){
        List<Order> list = null;

        if (StringUtils.hasText("id")){
            list = orderService.findByOrderId(id);
        }else {
            list = orderService.findAll();
        }
        model.addAttribute("orders", list);
        return "orders/search";
    }
}
