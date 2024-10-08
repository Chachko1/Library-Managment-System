package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.LoginDTO;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.Enums.RoleEnum;
import org.librarymanagementsystem.mappers.MemberMapper;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Role;
import org.librarymanagementsystem.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private final PasswordEncoder encoder;

    public MemberService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    public List<MemberDTO> getAllMembers(){
        return memberRepository.findAll().stream().map(memberMapper::toDTO).collect(Collectors.toList());
    }

    public MemberDTO saveMember(MemberDTO memberDto){
        Member member=memberMapper.toEntity(memberDto);
        Role role;
        if (memberRepository.findAll().isEmpty()){
            role=new Role();
            role.setRoleEnum(RoleEnum.ADMIN);

        }else {
            role=new Role();
            role.setRoleEnum(RoleEnum.USER);

        }

        role.setRolesAddedBy(member);
        member.setRoles(Collections.singletonList(role));
        member.setPassword(encoder.encode(memberDto.getPassword()));
        return memberMapper.toDTO(memberRepository.save(member));
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public boolean existsByUsername(String username){
        return memberRepository.existsByUsername(username);
    }



    public Member getMemberByUsername(String username) {
       return memberRepository.findByUsername(username);

    }

    public Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return findByUsername(username);
    }

    private Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findMemberById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void resetLoginStatus() {
        memberRepository.findAll().forEach(member-> {
            member.setRecommendedBook(false);
            memberRepository.save(member);
        });

    }

    public void updateBookStatus(Member member) {
        member.setRecommendedBook(true);
        memberRepository.save(member);
    }
}
