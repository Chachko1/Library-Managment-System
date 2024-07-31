package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.LoginDTO;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.config.UserSession;
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
    @Autowired
    private final UserSession userSession;

    public MemberService(UserSession userSession) {
        this.userSession = userSession;
    }

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

    public boolean existsByUsername(String username){
        return memberRepository.existsByUsername(username);
    }

    public boolean validateUser(LoginDTO loginDTO) {
        Member member=memberRepository.findByUsername(loginDTO.getUsername());
        if (member!=null&&member.getPassword().equals(loginDTO.getPassword())){
            userSession.login(member.getId(),loginDTO.getUsername());
            return true;
        }
        return false;
    }

    public Member getMemberByUsername(String username) {
       return memberRepository.findByUsername(username);

    }

    public Member findMemberById(long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
