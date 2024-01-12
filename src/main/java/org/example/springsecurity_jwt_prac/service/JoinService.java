package org.example.springsecurity_jwt_prac.service;

import org.apache.catalina.User;
import org.example.springsecurity_jwt_prac.dto.JoinDTO;
import org.example.springsecurity_jwt_prac.entity.UserEntity;
import org.example.springsecurity_jwt_prac.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    //주입
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JoinService(UserRepository userRespository, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.userRepository = userRespository;//초기화
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    //회원가입 진행 메서드 작성
    public void joinProcess(JoinDTO joinDTO){
        //앞단에서 날라오는 DTO를 받아야함


        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        //이후 유저 중복체크 메서드 만들러 감

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist){
            return; //false ㄴㄴ
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));//패스워드는 인코딩 해야함
        data.setRole("ROLE_ADMIN");


    }
}
