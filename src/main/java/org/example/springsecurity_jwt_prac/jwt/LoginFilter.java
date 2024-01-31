package org.example.springsecurity_jwt_prac.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.springsecurity_jwt_prac.dto.CustomMemberDetails;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    private static final String SPRING_SECURITY_FORM_MEMBERLOGINID_KEY = "memberLoginId";
    private static final String SPRING_SECURITY_FORM_MEMBERPASSWORD_KEY = "memberPassword";


    //새로운 파라미터 생성
    private String memberLoginIdParameter = SPRING_SECURITY_FORM_MEMBERLOGINID_KEY;
    private String memberPasswordParameter = SPRING_SECURITY_FORM_MEMBERPASSWORD_KEY;


    //아이디와 패스워드를 request에서 받아오기
    @Nullable
    protected String obtainMemberLoginId(HttpServletRequest request) {
        return request.getParameter(this.memberLoginIdParameter);
    }

    @Nullable
    protected String obtainMemberPassword(HttpServletRequest request) {
        return request.getParameter(this.memberPasswordParameter);
    }

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 memberLoginId, memberPassword 추출
        String memberLoginId = obtainMemberLoginId(request);
        String memberPassword = obtainMemberPassword(request);

        log.info("LoginFilter.attemptAuthentication() memberLoginId = {}", memberLoginId);
        log.info("LoginFilter.attemptAuthentication() memberPassword = {}", memberPassword);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(memberLoginId, memberPassword, null);
        log.info("authToken = {}",authToken);
        //token에 담은 검증을 위한 AuthenticationManager로 전달
        //authenticationManager가 검증 해줍니다.
        return authenticationManager.authenticate(authToken);

    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomMemberDetails customMemberDetails = (CustomMemberDetails) authentication.getPrincipal();

        String memberName = customMemberDetails.getUsername();

        //role 값 뽑아내기
        log.info("LoginFilter.successfulAuthentication() authentication = {}", authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("LoginFilter.successfulAuthentication() authorities = {}", authorities.toString());
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();



        String role = auth.getAuthority();

        //토큰생성
        String token = jwtUtil.createJwt(memberName, role, 60*60*10L);

        response.addHeader("Authorization", "Bearer " + token);

    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}
