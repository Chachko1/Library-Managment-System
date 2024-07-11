package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.ReviewDTO;
import org.librarymanagementsystem.services.BookService;
import org.librarymanagementsystem.services.MemberService;
import org.librarymanagementsystem.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
     private ReviewService reviewService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;



    @GetMapping
    public String getReviewsList(Model model){
        List<ReviewDTO> reviews=reviewService.getAllReviews();
        model.addAttribute("reviews",reviews);

        return "reviews/list";

    }

    @GetMapping("/new")
    public String showReviewForm(Model model){
        model.addAttribute("review",new ReviewDTO());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("books", bookService.getAllBooks());
        return "reviews/form";


    }

    @PostMapping
    public String saveReview(@Valid @ModelAttribute ReviewDTO reviewDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/reviews/form";
        }
        reviewService.saveReview(reviewDTO);
        return "redirect:/reviews";

    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id){
        reviewService.deleteReview(id);

        return "redirect:/reviews";

    }




}
