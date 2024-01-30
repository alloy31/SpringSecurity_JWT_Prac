package org.example.springsecurity_jwt_prac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String memberLoginId;
    private String memberPassword;
}
