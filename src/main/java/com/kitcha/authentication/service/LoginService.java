package com.kitcha.authentication.service;

import com.kitcha.authentication.Utils.JwtUtils;
import com.kitcha.authentication.dto.CustomUserDetails;
import com.kitcha.authentication.dto.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public List<String> authenticate(LoginDto dto) {
        // SecurityConfiguration's authenticationManager Bean 사용
        // 인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        
        // 인증 성공 후 사용자 정보 로드 및 토큰 생성
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return List.of(jwtUtils.generateToken(customUserDetails),
                Optional.ofNullable(customUserDetails.getRole()).orElse(""),
                Optional.ofNullable(customUserDetails.getInterest()).orElse(""));
    }
}
