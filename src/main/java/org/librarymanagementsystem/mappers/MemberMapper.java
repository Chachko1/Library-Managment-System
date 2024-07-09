package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.models.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity (MemberDTO memberDTO);

    MemberDTO toDTO (Member member);
}
