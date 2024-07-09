package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
