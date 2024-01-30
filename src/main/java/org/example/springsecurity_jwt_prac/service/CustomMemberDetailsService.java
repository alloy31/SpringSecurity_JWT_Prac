package org.example.springsecurity_jwt_prac.service;

import org.example.springsecurity_jwt_prac.dto.CustomUserDetails;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomMemberDetailsService(MemberRepository memberRepository){
     this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String memberLoginId) throws UsernameNotFoundException {

        //DB에서 조회
        Member memberData = memberRepository.findByMemberLoginId(memberLoginId);

        if(memberData != null) {

            return new CustomUserDetails(memberData);
        }
        return null;
    }
}
