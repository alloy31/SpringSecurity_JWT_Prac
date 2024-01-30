package org.example.springsecurity_jwt_prac.service;

import org.example.springsecurity_jwt_prac.dto.JoinDTO;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    //주입
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JoinService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.memberRepository = memberRepository;//초기화
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    //회원가입 진행 메서드 작성
    public String joinProcess(JoinDTO joinDTO){
        //앞단에서 날라오는 DTO를 받아야함


        String memberLoginId = joinDTO.getMemberName();
        String memberPassword = joinDTO.getMemberPassword();
        String memberName = joinDTO.getMemberName();
        String memberEmail = joinDTO.getMemberEmail();

        //비어있는 항목이 있다면
        if(memberLoginId == null || memberPassword == null || memberName == null || memberEmail == null){
            return "null exists";
        }

        //유저 아이디로 중복체크
        Boolean isExist = memberRepository.existsByMemberLoginId(memberLoginId);


        if(isExist){
            return "ID exists"; //false ㄴㄴ
        }

        Member data = new Member();

        data.setMemberLoginId(memberLoginId);
        data.setMemberPassword(bCryptPasswordEncoder.encode(memberPassword));//패스워드는 인코딩 해야함
        data.setMemberName(memberName);
        data.setMemberEmail(memberEmail);
        //유저 롤 일단 나중에 고치기
        data.setRole("ROLE_ADMIN");
        //유저 네임과 패스워드를 요청받아서 넣을거임

        memberRepository.save(data);
        return "Join success";

    }
}
