package org.example.springsecurity_jwt_prac.service;

import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }


}
