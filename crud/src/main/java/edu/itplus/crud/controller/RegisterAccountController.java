package edu.itplus.crud.controller;

import edu.itplus.crud.domain.Account;
import edu.itplus.crud.model.AccountDto;
import edu.itplus.crud.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@RequestMapping("register")
@Controller

public class RegisterAccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("")
    public String add(Model model){
        model.addAttribute("account",new AccountDto());
        return "accounts/register";
    }
    @PostMapping("/save")
    public ModelAndView save(ModelMap model, @Valid @ModelAttribute("account")
    AccountDto dto, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("accounts/register");
        }
        Account entity = new Account();
        BeanUtils.copyProperties(dto,entity);


        accountService.save(entity);
        model.addAttribute("message","account ís saved");

        return new ModelAndView("forward:/admin/accounts", model);
    }
}
