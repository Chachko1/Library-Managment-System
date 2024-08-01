package org.librarymanagementsystem.services;

import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Role;
import org.librarymanagementsystem.repositories.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class LBMUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;



    public LBMUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username);
        UserDetails mappedMember = mapped(member);

        if (mappedMember.toString().isEmpty()){
            throw  new UsernameNotFoundException("User with " + username + " not found");
        } else {
            return mappedMember;
        }
    }

    private static UserDetails mapped(Member member){
        return User
                .withUsername(member.getUsername())
                .password(member.getPassword())
                .authorities(mapRoles(member.getRoles()))
                .disabled(false)
                .build();
    }

    private static Set<GrantedAuthority> mapRoles(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()))
                .collect(Collectors.toSet());
    }
}

