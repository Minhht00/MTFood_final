package edu.itplus.crud.controller;


import edu.itplus.crud.domain.Category;
import edu.itplus.crud.domain.Product;
import edu.itplus.crud.model.CategoryDto;
import edu.itplus.crud.model.ProductDto;
import edu.itplus.crud.service.CategoryService;
import edu.itplus.crud.service.ProductService;
import edu.itplus.crud.service.StorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("admin/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StorageService storageService;
    @GetMapping("test")
    public String test(Model model) {

        return "products/test";
    }
    @GetMapping("add")
    public String add(Model model) {
        ProductDto dto = new ProductDto();

        dto.setIsEdit(false);
        model.addAttribute("product", dto);
        return "products/addOrEdit";
    }

    @GetMapping("edit/{productId}")
    public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
        Optional<Product> opt = productService.findById(productId);
        ProductDto dto = new ProductDto();

        if (opt.isPresent()) {
            Product entity = opt.get();

            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);

            model.addAttribute("product", dto);
            return new ModelAndView("products/addOrEdit", model);
        }
        model.addAttribute("message", "category is not existed");
        return new ModelAndView("forward: /admin/products", model);
    }

    @ModelAttribute("categories")
    public List<CategoryDto> getCategories() {
        return categoryService.findAll().stream().map(item -> {
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }
//    @ModelAttribute("cate/{categoryId}")
//    public String getCateg(@PathVariable("categoryId") Long categoryId) {
//        Optional<Category> opt = categoryService.findById(categoryId);
//        return opt.get().getName();
//
//    }
    @GetMapping("delete/{productId}")
    public ModelAndView delete(ModelMap model, @PathVariable("productId") Long productId) throws IOException {
        Optional<Product> opt = productService.findById(productId);

        if (opt.isPresent()){
            if (!StringUtils.isEmpty(opt.get().getImage())){
                storageService.delete(opt.get().getImage());
            }
            productService.delete(opt.get());

            model.addAttribute("message","Product is deleted");
        }
        else {
            model.addAttribute("message","Product is not found");

        }
        return new ModelAndView("forward:/admin/products");
    }

    @PostMapping("save")
    public ModelAndView save(ModelMap model, @Valid @ModelAttribute("product")
    ProductDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("products/addOrEdit");
        }
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);

        if (!dto.getImageFile().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String uuString = uuid.toString();

            entity.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));
            storageService.store(dto.getImageFile(), entity.getImage());
        }

        productService.save(entity);
        model.addAttribute("message", "Product ís saved");

        return new ModelAndView("forward:/admin/products", model);
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
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "products/list";
    }

@GetMapping("search")
    public String search(ModelMap model, @RequestParam(value = "name", required = false) String name){
        List<Product> list = null;

        if (StringUtils.hasText("name")){
            list = productService.findByNameContaining(name);
        }else {
            list = productService.findAll();
        }
        model.addAttribute("products", list);
        return "products/search";
    }
}
