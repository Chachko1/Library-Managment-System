package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.config.UserSession;
import org.librarymanagementsystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserSession userSession;

    @GetMapping
    public String listAuthors(Model model){
        if (!userSession.isLoggedIn()){
             return "redirect:/login";
        }
        List<AuthorDTO> authors=authorService.getAllAuthors();
        model.addAttribute("authors",authors);
        return "/authors/list";



    }

    @GetMapping("/new")
    public String showAuthorForm(Model model){
        if (!userSession.isLoggedIn()){
            return "redirect:/login";
        }
        model.addAttribute("author",new AuthorDTO() );
        return "/authors/form";

    }

    @PostMapping
    public String saveAuthor(@Valid @ModelAttribute("author") AuthorDTO authorDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "authors/form";
        }
        authorService.saveAuthor(authorDTO);
        return "redirect:/authors";

    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }



}
