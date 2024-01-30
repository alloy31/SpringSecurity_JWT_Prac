package org.example.springsecurity_jwt_prac.service;

import org.example.springsecurity_jwt_prac.dto.LoginDTO;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    public String loginProcess(LoginDTO loginDTO){

        String memberLoginId = loginDTO.getMemberLoginId();
        String memberPassword = loginDTO.getMemberPassword();

        //비어있는 항목이 있을 때

        Member data = memberRepository.findByMemberLoginId(memberLoginId);

        if(data != null){

        }


    }


}
