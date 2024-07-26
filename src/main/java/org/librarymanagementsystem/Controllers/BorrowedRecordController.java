package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.services.BookService;
import org.librarymanagementsystem.services.BorrowRecordService;
import org.librarymanagementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@Controller
@RequestMapping("/borrowedRecords")
public class BorrowedRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String getBorrowedRecordsList(Model model){
        List<BorrowRecordDTO> borrowRecords =borrowRecordService.getAllBorrowRecords();
        model.addAttribute("borrowedRecords",borrowRecords);

        List<MemberDTO> members = memberService.getAllMembers();
        model.addAttribute("members", members);

        List<BookDTO> books=bookService.getAllBooks();
        model.addAttribute("books",books);

        return "borrowedRecords/list";


    }

    @GetMapping("/new")
    public String showRecordForm(Model model){
        model.addAttribute("borrowedRecord",new BorrowRecordDTO());

        List<MemberDTO> members = memberService.getAllMembers();
        model.addAttribute("members", members);

        List<BookDTO> books=bookService.getAllBooks();
        model.addAttribute("books",books);


        return "borrowedRecords/form";


    }

    @PostMapping
    public String saveBorrowedRecord(@Valid @ModelAttribute BorrowRecordDTO borrowRecordDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "borrowedRecords/form";
        }
        borrowRecordService.saveBorrowRecord(borrowRecordDTO);
        return "redirect:/borrowedRecords";

    }
    @GetMapping("delete/{id}")
    public String deleteBorrowedRecord(@PathVariable("id") Long id ){
        borrowRecordService.deleteBorrowRecord(id);

        return "redirect:/borrowedRecords";

    }


}
