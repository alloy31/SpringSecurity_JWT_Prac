package org.example.springsecurity_jwt_prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity_jwt_prac.JoinRequestStatus;
import org.example.springsecurity_jwt_prac.dto.JoinRequestDto;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.service.JoinService;
import org.example.springsecurity_jwt_prac.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
//    @GetMapping("/")
//    public String mainP(){
//
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        //세션에서 이름 가져오기
//        SecurityContextHolder.getContext().getAuthentication().getName();
//
//        //세션에서 유저 롤 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
//        GrantedAuthority auth = iter.next();
//        String role = auth.getAuthority();
//
//        return "Main Controller : "+ name +" " + role;
//    }


    private final JoinService joinService;
    private final MemberService memberService;


    @GetMapping("/")
    public ResponseEntity<List<Member>> getAllUsers() {
        List<Member> memberList = memberService.getAllMembers();

        // 사용자 목록을 반환하거나, 비어있는 경우 NOT_FOUND(404)를 반환할 수도 있습니다.
        return memberList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(memberList);
    }
    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(JoinRequestDto joinRequestDto){

        JoinRequestStatus status = joinService.joinProcess(joinRequestDto);

        return switch (status) {
            case NULL_EXIST -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            case ID_DUPLICATED -> new ResponseEntity<>(HttpStatus.CONFLICT);
            case JOIN_SUCCESS -> new ResponseEntity<>(HttpStatus.OK);
        };

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        // Spring Security에서 현재 인증 정보를 가져와 로그아웃 처리
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //회원 탈퇴
    //현재 로그인 사용자 == 탈퇴할 사용자
    //토큰이 만료되지 않았어야함

    //회원 정보 수정
    //현재 로그인 사용자 == 수정할 사용자
    //토큰이 만료되지 않았어야함

    //모든 사용자 검색







}
