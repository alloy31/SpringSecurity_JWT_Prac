package org.example.springsecurity_jwt_prac.service;

import org.example.springsecurity_jwt_prac.dto.JoinDTO;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    //주입
    private final MemberRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JoinService(MemberRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.userRepository = userRepository;//초기화
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    //회원가입 진행 메서드 작성
    public void joinProcess(JoinDTO joinDTO){
        //앞단에서 날라오는 DTO를 받아야함


        String membername = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        //이후 유저 중복체크 메서드 만들러 감

        Boolean isExist = userRepository.existsByMemberLoginId(membername);

        if(isExist){
            return; //false ㄴㄴ
        }

        Member data = new Member();

        data.setMemberName(membername);
        data.setMemberPassword(bCryptPasswordEncoder.encode(password));//패스워드는 인코딩 해야함
        data.setRole("ROLE_ADMIN");
        //유저 네임과 패스워드를 요청받아서 넣을거임

        userRepository.save(data);

    }
}
