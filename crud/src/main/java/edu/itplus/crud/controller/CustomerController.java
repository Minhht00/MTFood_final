package edu.itplus.crud.controller;



import edu.itplus.crud.domain.Customer;

import edu.itplus.crud.model.CustomerDto;
import edu.itplus.crud.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("customer",new CustomerDto());
        return "customers/addOrEdit";
    }
    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model,@PathVariable("id") Long id){
        Optional<Customer> opt = customerService.findById(id);
        CustomerDto dto = new CustomerDto();

        if (opt.isPresent()){
            Customer entity = opt.get();

            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);

            dto.setPassword("");

            model.addAttribute("customer", dto);
            return new ModelAndView("customers/addOrEdit", model);
        }
        model.addAttribute("message","account is not existed");
        return new ModelAndView("forward: /admin/customers", model);
    }
    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Long id){
        customerService.deleteById(id);
        model.addAttribute("message", "account is deleted!");
        return new ModelAndView("forward:/admin/customers");
    }
//    @GetMapping("/images/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
    @PostMapping("save")
    public ModelAndView save(ModelMap model, @Valid @ModelAttribute("customer")
                             CustomerDto dto, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("customers/addOrEdit");
        }
        Customer entity = new Customer();
        BeanUtils.copyProperties(dto,entity);


        customerService.save(entity);
        model.addAttribute("message","customer ís saved");

        return new ModelAndView("forward:/admin/customers", model);
    }
//    @GetMapping("update")
//    public String update(){
//        return "redirect:/customers";
//    }
    @RequestMapping("")
    public String list(ModelMap model){
        List<Customer> list = customerService.findAll();
        model.addAttribute("customers",list);
        return "customers/list";
    }
    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(value = "name", required = false) String name){
        List<Customer> list = null;

        if (StringUtils.hasText("name")){
            list = customerService.findByNameContaining(name);
        }else {
            list = customerService.findAll();
        }
        model.addAttribute("customers", list);
        return "customers/search";
    }
}
