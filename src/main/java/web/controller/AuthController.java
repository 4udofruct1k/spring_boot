package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @GetMapping("/")
    public String redirectToLogin(HttpServletRequest request){
        return "redirect:" + request.getContextPath() + "/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/logout-success")
    public String logoutPage(){
        return "redirect:/login?logout";
    }
}
