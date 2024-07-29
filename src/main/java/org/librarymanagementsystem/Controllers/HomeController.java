package org.librarymanagementsystem.Controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.LoginDTO;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.config.UserSession;
import org.librarymanagementsystem.services.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    private final MemberService memberService;
    private final UserSession userSession;


    public HomeController(MemberService memberService, UserSession userSession) {
        this.memberService = memberService;
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(){
        return "register";
    }
    @ModelAttribute("registerData")
    public MemberDTO registerDTO(){
        return new MemberDTO();
    }
    @ModelAttribute("loginData")
    public LoginDTO loginDTO(){
        return new LoginDTO();
    }
    @PostMapping("/register")
    public String registerMember(MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (memberService.existsByUsername(memberDTO.getUsername())){
            bindingResult.rejectValue("username", "error.registerData", "Member already exists!");
        }
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerData",memberDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData",bindingResult);
            return "redirect:/register";
        }
       memberService.saveMember(memberDTO);


        return "redirect:/login";
    }

    @PostMapping("/login")
    public String completeLogin(@Valid LoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes ,HttpSession session){
        if (!memberService.validateUser(loginDTO)){
            bindingResult.reject("loginError","Invalid Username or password");
            session.setAttribute("loginError" ,"invalid Username or Password");

        }else {
            session.removeAttribute("loginError");
        }
        if (bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("loginData",loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData",bindingResult);

            return "redirect:/login";
        }

        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(){
        if (!userSession.isLoggedIn()){
            return "redirect:/";
        }
        userSession.logout();
        return "redirect:/";
    }








}
