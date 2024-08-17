package org.librarymanagementsystem.Controllers;

import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Review;
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
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;


    @GetMapping("/review-form")

    public String getReviewForm(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        model.addAttribute("review", new Review());
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return ("/reviews/review-form");
    }

    @GetMapping("/reviews/review-list")

    public String getReviewList(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        List<Review> reviews = reviewService.findAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews/review-list";
    }


    @PostMapping("/reviews/review-form")
    public String saveReview(@ModelAttribute("review") Review review,
                             BindingResult result,
                             @RequestParam("bookTitle") String bookTitle) {
        if (result.hasErrors()) {
            return "reviews/review-form";
        }

        Member member = memberService.findMemberById(memberService.getCurrentMember().getId());
        Book book = bookService.findBookByTitle(bookTitle);

        review.setMember(member);
        review.setBook(book);

        reviewService.saveReview(review);

        return "redirect:/reviews/review-list";
    }


    @GetMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReviewById(id);
        return "redirect:/reviews/review-list";
    }
}

