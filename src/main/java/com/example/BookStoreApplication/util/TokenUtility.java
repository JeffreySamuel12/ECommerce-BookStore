package com.example.BookStoreApplication.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.BookStoreApplication.dto.DataHolder;

public class TokenUtility {
    private final String SECRET = "09876543211234567890";
    public String getToken(Long id, String role) {
        return JWT.create()
                .withClaim("id", id)
                .withClaim("role", role)
                .sign(Algorithm.HMAC256(SECRET));
    }
    public DataHolder decode(String token) {
        Long id = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim("id").asLong();
        String role = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim("role").asString();
        DataHolder dataHolder=new DataHolder();
        dataHolder.setId(id);
        dataHolder.setRole(role);
        return dataHolder;
    }
}