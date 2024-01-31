package org.example.springsecurity_jwt_prac.dto;

import lombok.ToString;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
public class CustomMemberDetails implements UserDetails {

    private final Member member;

    public CustomMemberDetails(Member member){
        this.member = member;
    }

    //유저 권한 가져오기
    @Override
    public List<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add((GrantedAuthority) member::getMemberRole);
        return authorityList;
    }

    public String getMemberPassword() {return member.getMemberPassword();}

    public String getMemberLoginId(){ return member.getMemberLoginId();}

    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberLoginId();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
