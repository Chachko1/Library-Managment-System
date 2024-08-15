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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/list")
    public String getAllListMembers(Model model){
        Member currentMember=memberService.getCurrentMember();
        if (currentMember==null){
            return "redirect:/login";
        }
        List<MemberDTO> members = memberService.getAllMembers().stream()
                .filter(member -> !member.getUsername().equals(currentMember.getUsername()))
                .toList();

        model.addAttribute("noOtherMembers", members.isEmpty());  // <-- This ensures that it's never null
        model.addAttribute("members", members);

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
            return "members/list";
        }

        memberService.saveMember(memberDTO);
        return "redirect:/members/list";

    }

    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id ){
        memberService.deleteMember(id);
        return "redirect:/members/list";

    }




}
