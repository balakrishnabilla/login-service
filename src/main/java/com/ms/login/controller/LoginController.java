package com.ms.login.controller;

import com.ms.login.models.Response;
import com.ms.login.models.UserInfo;
import com.ms.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.status;

@RestController
class LoginController {


    @Autowired
    private LoginService loginService;

    @GetMapping("/hello")
    public String firstPage() {
        return "Hello World";
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserInfo userInfo) {
        try {
            String jwt = loginService.authenticate(userInfo);
            return ResponseEntity.ok(new Response(jwt,"Usr is authenticated successfully"));
        } catch (Exception e) {
            return unauthorize().body(new Response(null, e.getMessage()));
            //ResponseEntity.badRequest().build();

        }
    }

    public static ResponseEntity.BodyBuilder unauthorize() {
        return status(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserInfo userInfo) throws Exception {
        try {
            String userId = loginService.createUser(userInfo);
            URI location =
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(userId)
                            .toUri();

            String message = "User - " + userInfo.getUserid() + "is created successfully";
            return ResponseEntity.created(location).body(message);
          //  return ResponseEntity.ok("User - " + userInfo.getUserid() + " is created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}