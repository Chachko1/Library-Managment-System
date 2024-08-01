package org.librarymanagementsystem.Controllers;

import org.librarymanagementsystem.DTOs.LoginDTO;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Role;
import org.librarymanagementsystem.services.BookService;
import org.librarymanagementsystem.services.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HomeController {
    private final MemberService memberService;

    private final BookService bookService;


    public HomeController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;

        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){
        Member currentMember=memberService.getCurrentMember();

        if (memberService.getCurrentMember()==null) {

            return "index";
        }else{
            String message="Add Books so we can recommend you every day a book!";

            if (!currentMember.isRecommendedBook()){
                Optional<Book> book=bookService.getRandomBook();
                if (book.isPresent()){
                    message=book.get().getTitle();
                }
                model.addAttribute("showRecommendedBook",true);
                model.addAttribute("bookTitle",message);
                memberService.updateBookStatus(currentMember);
            }else {
                model.addAttribute("showRecommendedBook",false);
            }


        }

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








}
