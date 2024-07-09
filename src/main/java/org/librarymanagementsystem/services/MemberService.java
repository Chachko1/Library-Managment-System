package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.mappers.MemberMapper;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;

    public List<MemberDTO> getAllMembers(){
        return memberRepository.findAll().stream().map(memberMapper::toDTO).collect(Collectors.toList());
    }

    public MemberDTO saveMember(MemberDTO memberDto){
        Member member=memberMapper.toEntity(memberDto);
        return memberMapper.toDTO(memberRepository.save(member));
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

}
