package org.example.springsecurity_jwt_prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity_jwt_prac.dto.JoinDTO;
import org.example.springsecurity_jwt_prac.service.JoinService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
public class MemberController {
    @GetMapping("/")
    public String mainP(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        //세션에서 이름 가져오기
        SecurityContextHolder.getContext().getAuthentication().getName();

        //세션에서 유저 롤 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main Controller : "+ name +" " + role;
    }


    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){

        System.out.println(joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "OK";
    }



}
