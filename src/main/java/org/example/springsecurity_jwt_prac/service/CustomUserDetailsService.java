package org.example.springsecurity_jwt_prac.service;

import org.example.springsecurity_jwt_prac.dto.CustomUserDetails;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository){
     this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member userData = memberRepository.findByMemberLoginId(username);

        if(userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
