package org.librarymanagementsystem.Controllers;

import jakarta.validation.Valid;
import org.librarymanagementsystem.DTOs.MemberDTO;

import org.librarymanagementsystem.models.Member;

import org.librarymanagementsystem.services.MemberService;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/members")
public class RestLibraryController {
   private MemberService memberService;
   private ModelMapper modelMapper;

    public RestLibraryController(MemberService memberService, ModelMapper modelMapper) {
        this.memberService = memberService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/current")
    public ResponseEntity<MemberDTO> getCurrentMember(){
        Member member=memberService.getCurrentMember();
        if (member==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MemberDTO memberDTO=modelMapper.map(member,MemberDTO.class);
        memberDTO.setUsername(member.getUsername());
        memberDTO.setPassword(null);
        return new ResponseEntity<>(memberDTO,HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberDTO memberDTO) {

        Member currentMember = memberService.getCurrentMember();
        if (currentMember == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

       memberService.saveMember(memberDTO);


        return new ResponseEntity<>(memberDTO, HttpStatus.CREATED);
    }

}
