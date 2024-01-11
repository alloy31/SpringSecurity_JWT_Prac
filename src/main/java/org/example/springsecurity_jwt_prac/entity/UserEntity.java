package org.example.springsecurity_jwt_prac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity //어노테이션으로 엔티티 설정
@Getter
@Setter //롬복으로 게터세터 자동생성
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //아이덴티티값으로 설정해줌
    private int id;

    private String username;
    private String password;

    private String role;
}
