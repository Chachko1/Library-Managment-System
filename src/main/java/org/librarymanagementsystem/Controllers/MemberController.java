package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @GetMapping
    public String getAllListMembers(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        List<MemberDTO> members=memberService.getAllMembers();
        model.addAttribute("members",members);

        return "members/list";
    }

    @GetMapping("/new")
    public String showMemberForm(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        model.addAttribute("member",new MemberDTO());

        return "register";

    }

    @PostMapping
    public String saveMember(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "members/form";
        }

        memberService.saveMember(memberDTO);
        return "redirect:/members";

    }

    @GetMapping("delete/{id}")
    public String deleteMember(@PathVariable Long id ){
        memberService.deleteMember(id);
        return "redirect:/members";

    }




}
