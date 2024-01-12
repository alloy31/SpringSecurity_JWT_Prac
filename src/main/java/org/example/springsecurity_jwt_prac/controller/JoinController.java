package org.example.springsecurity_jwt_prac.controller;

import org.example.springsecurity_jwt_prac.dto.JoinDTO;
import org.example.springsecurity_jwt_prac.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody //api 서버를 위함
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }
    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){

        return "OK";
    }

}
