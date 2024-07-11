package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.services.BorrowRecordService;
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

    @GetMapping
    public String getBorrowedRecordsList(Model model){
        List<BorrowRecordDTO> borrowRecords =borrowRecordService.getAllBorrowRecords();
        model.addAttribute("borrowedRecords",borrowRecords);
        return "borrowedRecords/list";


    }

    @GetMapping("/new")
    public String showRecordForm(Model model){
        model.addAttribute("borrowedRecord",new BorrowRecordDTO());
        return "borrowedRecord/form";


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
