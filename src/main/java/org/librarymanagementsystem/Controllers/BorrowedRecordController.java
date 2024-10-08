package org.librarymanagementsystem.Controllers;

import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.services.BookService;
import org.librarymanagementsystem.services.BorrowRecordService;
import org.librarymanagementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/borrowedRecords")
public class BorrowedRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;



    @GetMapping("/borrowed-records-list")
    public String getBorrowedRecordsList(Model model) {
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        List<BorrowRecord> borrowRecords = borrowRecordService.findAllBorrowRecords();
        model.addAttribute("borrowRecords", borrowRecords);
        return "borrowedRecords/borrowed-records-list";
    }

    @GetMapping("/borrowed-records-form")
    public String getBorrowedRecordsForm(Model model) {
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        model.addAttribute("borrowRecord", new BorrowRecord());
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "borrowedRecords/borrowed-records-form";
    }

    @PostMapping("/borrowed-records-form")
    public String saveBorrowRecord(@ModelAttribute("borrowRecord") BorrowRecord borrowRecord,
                                   BindingResult result,
                                   @RequestParam("bookTitle") String bookTitle,
                                   @RequestParam("borrowedDate") String borrowedDate,
                                   @RequestParam("returnedDate") String returnedDate,
                                   Model model) {
        if (result.hasErrors()) {
            return "borrowedRecords/borrowed-records-form";
        }

        Member member = memberService.findMemberById(memberService.getCurrentMember().getId());
        Book book = bookService.findBookByTitle(bookTitle);

        BorrowRecord borrowRecord1 = new BorrowRecord();
        borrowRecord1.setMember(member);
        borrowRecord1.setBook(book);
        borrowRecord1.setBorrowedDate(LocalDate.parse(borrowedDate));
        borrowRecord1.setReturnedDate(LocalDate.parse(returnedDate));

        Optional<BorrowRecord> latestBorrowRecord = borrowRecordService.findLatestBorrowRecordByBookId(book.getId());


        if (latestBorrowRecord.isPresent() && borrowRecord1.getBorrowedDate().isAfter(latestBorrowRecord.get().getReturnedDate())){
            borrowRecordService.saveBorrowRecord(borrowRecord1);
            bookService.updateBookBorrowedStatus(book.getId(), true);
            return "redirect:/borrowedRecords/borrowed-records-list";
        }

        if (borrowRecord1.getBorrowedDate().isBefore(LocalDate.now())) {
            model.addAttribute("errorMessage", "Impossible to borrow book with date before: " + LocalDate.now());
            List<Book> books = bookService.findAllBooks();
            model.addAttribute("books", books);
            return "borrowedRecords/borrowed-records-form";

        } else if (latestBorrowRecord.isPresent() && latestBorrowRecord.get().getReturnedDate().isAfter(LocalDate.now())) {
            model.addAttribute("errorMessage", "This book is borrowed by another user until: " + latestBorrowRecord.get().getReturnedDate());
            List<Book> books = bookService.findAllBooks();
            model.addAttribute("books", books);
            return "borrowedRecords/borrowed-records-form";
        } else {
            bookService.updateBookBorrowedStatus(book.getId(), false);
        }



        borrowRecordService.saveBorrowRecord(borrowRecord1);
        bookService.updateBookBorrowedStatus(book.getId(), true);

        return "redirect:/borrowedRecords/borrowed-records-list";
    }
}