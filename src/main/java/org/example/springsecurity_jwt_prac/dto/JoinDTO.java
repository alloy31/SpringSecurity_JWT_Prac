package org.example.springsecurity_jwt_prac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {
    private String memberLoginId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;

}
