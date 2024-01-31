package org.example.springsecurity_jwt_prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity_jwt_prac.JoinRequestStatus;
import org.example.springsecurity_jwt_prac.dto.JoinRequestDto;
import org.example.springsecurity_jwt_prac.dto.LoginDTO;
import org.example.springsecurity_jwt_prac.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> joinProcess(JoinRequestDto joinRequestDto){

        JoinRequestStatus status = joinService.joinProcess(joinRequestDto);

        switch (status){

            case  NULL_EXIST:
                return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
            case ID_DUPLICATED:
                return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
            case JOIN_SUCCESS:
                return new ResponseEntity<> (HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginProcess(LoginDTO loginDTO){
//
//        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//
//    }



}
