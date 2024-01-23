package org.example.springsecurity_jwt_prac.repository;

import org.example.springsecurity_jwt_prac.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> { // JPA 레포지토리 상속받고, 엔티티와 id의 레퍼런스 타입 입력받기

    //JPA 구문 중 existBy가 있음
    Boolean existsByMemberLoginId(String username);

    //username을 받아 DB테이블에서 회원을 조회하는 메서드 작성
    Member findByMemberLoginId(String username);



}
