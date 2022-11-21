package edu.itplus.crud.controller;


import edu.itplus.crud.domain.Category;
import edu.itplus.crud.model.CategoryDto;
import edu.itplus.crud.service.CategoryService;
import edu.itplus.crud.service.StorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    StorageService storageService;

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("category",new CategoryDto());
        return "categories/addOrEdit";
    }
    @GetMapping("edit/{categoryId}")
    public ModelAndView edit(ModelMap model,@PathVariable("categoryId") Long categoryId){
        Optional<Category> opt = categoryService.findById(categoryId);
        CategoryDto dto = new CategoryDto();

        if (opt.isPresent()){
            Category entity = opt.get();

            BeanUtils.copyProperties(entity, dto);

            dto.setIsEdit(true);

            model.addAttribute("category", dto);
            return new ModelAndView("categories/addOrEdit", model);
        }
        model.addAttribute("message","category is not existed");
        return new ModelAndView("forward: /admin/categories", model);
    }
    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId){
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "Category is deleted!");
        return new ModelAndView("forward:/admin/categories");
    }
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    @PostMapping("save")
    public ModelAndView save(ModelMap model, @Valid @ModelAttribute("category")
                             CategoryDto dto, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("categories/addOrEdit");
        }
        Category entity = new Category();
        BeanUtils.copyProperties(dto,entity);

        if (!dto.getImageFile().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String uuString = uuid.toString();

            entity.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));
            storageService.store(dto.getImageFile(), entity.getImage());
        }

        categoryService.save(entity);
        model.addAttribute("message","Category ís saved");

        return new ModelAndView("forward:/admin/categories", model);
    }

    @RequestMapping("")
    public String list(ModelMap model){
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories",list);
        return "categories/list";
    }
    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(value = "name", required = false) String name){
        List<Category> list = null;

        if (StringUtils.hasText("name")){
            list = categoryService.findByNameContaining(name);
        }else {
            list = categoryService.findAll();
        }
        model.addAttribute("categories", list);
        return "categories/search";
    }
}
