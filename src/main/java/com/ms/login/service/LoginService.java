package com.ms.login.service;

import com.ms.login.models.UserInfo;
import com.ms.login.repository.LoginRepository;
import com.ms.login.util.JwtUtil;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private LoginRepository loginRepository;

    public String authenticate(UserInfo userInfo) throws Exception {
        Authentication authentication = null;
        try {
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userInfo.getUserid(), userInfo.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userInfo.getUserid());

        return jwtTokenUtil.generateToken(userDetails);

    }

    public String createUser(UserInfo userInfo) throws Exception {

       return loginRepository.save(userInfo).getUserid();


    }


}
