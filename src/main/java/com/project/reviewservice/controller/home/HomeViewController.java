package com.project.reviewservice.controller.home;

import com.project.reviewservice.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeViewController {

    @Autowired
    UserService userService;


    @GetMapping("/")  //홈페이지 접근 시 로그인으로 유도
    public String home(HttpSession session , Model model) {

        //세션 여부 확인 -> 세션 없으면 login, 있으면 main
        if(session.getAttribute("username") == null) {
            return "/login";
        }

        model.addAttribute("username" , session.getAttribute("username"));
        return "main";
    }


    @GetMapping("/login")
    public String login(HttpSession session , RedirectAttributes redirectAttributes) {
        //세션 여부 확인 -> 세션 없으면 login, 있으면 main
        if(session.getAttribute("username") == null) {
            return "/login";
        }

        redirectAttributes.addFlashAttribute("username", session.getAttribute("username"));
        redirectAttributes.addFlashAttribute("userId", session.getAttribute("userId"));
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(HttpSession session , Model model) {

        if(session.getAttribute("username") == null) {
            return "/login";
        }
        model.addAttribute("username" , session.getAttribute("username"));
        model.addAttribute("userId", session.getAttribute("userId"));
        return "main";
    }


}
