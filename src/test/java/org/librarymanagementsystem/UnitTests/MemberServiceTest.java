package org.librarymanagementsystem.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.Enums.RoleEnum;
import org.librarymanagementsystem.mappers.MemberMapper;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Role;
import org.librarymanagementsystem.repositories.MemberRepository;
import org.librarymanagementsystem.services.MemberService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    private Member member;
    private MemberDTO memberDTO;
    private Role role;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the Member, MemberDTO, and Role
        member = new Member(1L, "testuser", "password");
        memberDTO = new MemberDTO(1L, "testuser", "password", new ArrayList<>(), new ArrayList<>());
        role = new Role();
        role.setRoleEnum(RoleEnum.USER);
    }

    @Test
    public void testGetAllMembers() {
        List<Member> members = new ArrayList<>();
        members.add(member);

        List<MemberDTO> memberDTOs = new ArrayList<>();
        memberDTOs.add(memberDTO);

        when(memberRepository.findAll()).thenReturn(members);
        when(memberMapper.toDTO(any(Member.class))).thenReturn(memberDTO);

        List<MemberDTO> result = memberService.getAllMembers();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(memberDTO, result.get(0));
    }

    @Test
    public void testSaveMember() {
        // Mock the necessary methods
        when(memberMapper.toEntity(any(MemberDTO.class))).thenReturn(member);
        when(memberRepository.findAll()).thenReturn(new ArrayList<>()); // Returns an empty list, meaning the role will be ADMIN
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        when(memberMapper.toDTO(any(Member.class))).thenReturn(memberDTO);
        lenient().when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Execute the method under test
        MemberDTO result = memberService.saveMember(memberDTO);

        // Verify interactions
        verify(memberMapper).toEntity(memberDTO); // Ensure mapper is used
        verify(memberRepository).save(member); // Ensure repository save is called
        verify(memberMapper).toDTO(member); // Ensure mapper toDTO is called

        // Assertions
        assertNotNull(result);
        assertEquals(memberDTO.getId(), result.getId());
        assertEquals(memberDTO.getUsername(), result.getUsername());
        assertEquals(memberDTO.getPassword(), result.getPassword());
        assertEquals(memberDTO.getBorrowRecords(), result.getBorrowRecords());
        assertEquals(memberDTO.getReviews(), result.getReviews());
    }



    @Test
    public void testDeleteMember() {
        memberService.deleteMember(1L);
        verify(memberRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testExistsByUsername() {
        when(memberRepository.existsByUsername(anyString())).thenReturn(true);
        boolean result = memberService.existsByUsername("testuser");
        assertTrue(result);
    }

    @Test
    public void testGetMemberByUsername() {
        when(memberRepository.findByUsername(anyString())).thenReturn(member);
        Member result = memberService.getMemberByUsername("testuser");
        assertNotNull(result);
        assertEquals(member, result);
    }

    @Test
    public void testGetCurrentMember() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContextHolder.setContext(securityContext);

        when(memberRepository.findByUsername(anyString())).thenReturn(member);

        Member result = memberService.getCurrentMember();
        assertNotNull(result);
        assertEquals(member, result);
    }

    @Test
    public void testFindMemberById() {
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        Member result = memberService.findMemberById(1L);
        assertNotNull(result);
        assertEquals(member, result);
    }

    @Test
    public void testResetLoginStatus() {
        List<Member> members = new ArrayList<>();
        members.add(member);

        when(memberRepository.findAll()).thenReturn(members);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        memberService.resetLoginStatus();
        assertFalse(member.isRecommendedBook());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void testUpdateBookStatus() {
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        memberService.updateBookStatus(member);
        assertTrue(member.isRecommendedBook());
        verify(memberRepository, times(1)).save(member);
    }
}

