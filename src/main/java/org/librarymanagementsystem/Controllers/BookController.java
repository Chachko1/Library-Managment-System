package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.services.AuthorService;
import org.librarymanagementsystem.services.BookService;
import org.librarymanagementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String listBooks(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        List<BookDTO> books = bookService.getAllBooks();
        model.addAttribute("books",books);

        List<AuthorDTO> authors = authorService.getAllAuthors();
        model.addAttribute("authors",authors);

        return "books/list";
    }

    @GetMapping("/new")
    public String showBookForm(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        model.addAttribute("book", new BookDTO());

        List<AuthorDTO> authors = authorService.getAllAuthors();
        model.addAttribute("authors",authors);

        return "books/form";
    }

    @PostMapping
    public String saveBook(@Valid @ModelAttribute("book") BookDTO book, BindingResult result){
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model){
        BookDTO book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "books/form";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id,@Valid @ModelAttribute("book") BookDTO bookDto, BindingResult result){
        if (result.hasErrors()){
            return "books/form";
        }
        bookService.saveBook(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBookAndReviews(id);
        return "redirect:/books";
    }
}
