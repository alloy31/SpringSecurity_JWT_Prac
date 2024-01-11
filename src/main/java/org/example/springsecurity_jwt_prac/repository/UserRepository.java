package org.example.springsecurity_jwt_prac.repository;

import org.example.springsecurity_jwt_prac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> { // JPA 레포지토리 상속받고, 엔티티와 id의 레퍼런스 타입 입력받기

    //JPA 구문 중 existBy가 있음
    Boolean existsByUsername(String username);

}
