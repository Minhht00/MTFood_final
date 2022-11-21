package edu.itplus.crud.controller;

import edu.itplus.crud.domain.Account;
import edu.itplus.crud.model.AdminLoginDto;
import edu.itplus.crud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AdminLoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpSession session;

    @GetMapping("login")
    public String login(ModelMap model){
        model.addAttribute("account",new AdminLoginDto());
        return "/accounts/login";
    }

    @PostMapping("login")
    public ModelAndView login(ModelMap model,
                              @Valid @ModelAttribute("account") AdminLoginDto dto,
                              BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("/accounts/login",model);
        }

        Account account = accountService.login(dto.getUsername(),dto.getPassword());

        if (account == null){
            model.addAttribute("message","Invalid username or password");
            return new ModelAndView("/accounts/login",model);
        }
        session.setAttribute("username",account.getUsername());
        Object ruri = session.getAttribute("redirect-uri");

        if (ruri != null){
            session.removeAttribute("redirect-uri");
            return new ModelAndView("redirect:" + ruri);
        }


        //đường dẫn mặc định khi đăng nhập xong
        return new ModelAndView("forward:/categories",model);
    }
}
