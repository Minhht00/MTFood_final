package edu.itplus.crud.controller;



import edu.itplus.crud.domain.Account;
import edu.itplus.crud.model.AccountDto;
import edu.itplus.crud.service.AccountService;

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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("account",new AccountDto());
        return "accounts/addOrEdit";
    }
    @GetMapping("edit/{username}")
    public ModelAndView edit(ModelMap model,@PathVariable("username") String username){
        Optional<Account> opt = accountService.findById(username);
        AccountDto dto = new AccountDto();

        if (opt.isPresent()){
            Account entity = opt.get();

            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);

            dto.setPassword("");

            model.addAttribute("account", dto);
            return new ModelAndView("accounts/addOrEdit", model);
        }
        model.addAttribute("message","account is not existed");
        return new ModelAndView("forward: /admin/accounts", model);
    }
    @GetMapping("delete/{username}")
    public ModelAndView delete(ModelMap model, @PathVariable("username") String username){
        accountService.deleteById(username);
        model.addAttribute("message", "account is deleted!");
        return new ModelAndView("forward:/admin/accounts");
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
    public ModelAndView save(ModelMap model, @Valid @ModelAttribute("account")
                             AccountDto dto, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("accounts/addOrEdit");
        }
        Account entity = new Account();
        BeanUtils.copyProperties(dto,entity);


        accountService.save(entity);
        model.addAttribute("message","account ís saved");

        return new ModelAndView("forward:/admin/accounts", model);
    }
//    @GetMapping("update")
//    public String update(){
//        return "redirect:/accounts";
//    }
    @RequestMapping("")
    public String list(ModelMap model){
        List<Account> list = accountService.findAll();
        model.addAttribute("accounts",list);
        return "accounts/list";
    }
    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(value = "username", required = false) String username){
        List<Account> list = null;

        if (StringUtils.hasText("name")){
            list = accountService.findByUsernameContaining(username);
        }else {
            list = accountService.findAll();
        }
        model.addAttribute("accounts", list);
        return "accounts/search";
    }
}
