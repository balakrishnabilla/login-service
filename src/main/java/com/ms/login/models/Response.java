package com.ms.login.models;

import java.io.Serializable;

public class Response implements Serializable {

    private final String jwt;
    private String message;

    public Response(String jwt, String message) {

        this.jwt = jwt;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public String getMessage() {
        return message;
    }

}
