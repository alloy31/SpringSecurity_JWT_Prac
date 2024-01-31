package org.example.springsecurity_jwt_prac.service;

import lombok.extern.slf4j.Slf4j;
import org.example.springsecurity_jwt_prac.dto.CustomMemberDetails;
import org.example.springsecurity_jwt_prac.entity.Member;
import org.example.springsecurity_jwt_prac.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
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

        //아이디를 가진 사용자가 있으면
        if(memberData != null) {
            if(memberData.getMemberIsAvailable() == 1) return null;
            return new CustomMemberDetails(memberData);
        }

        //아이디를 가진 사용자가 없으면
        return null;
    }
}
