package org.example.springsecurity_jwt_prac.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurity_jwt_prac.dto.JoinDTO;
import org.example.springsecurity_jwt_prac.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody //api 서버를 위함
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;


    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){

        System.out.println(joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "OK";
    }

    

}
