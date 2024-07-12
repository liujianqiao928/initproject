package com.ljq.project.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ljq.project.model.User;

import java.time.Instant;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String JWT_SECRET = "jwt_secret";

        public static String generateToken(User user) {
            Calendar instance = Calendar.getInstance();
            // 默认30 s
            instance.add(Calendar.SECOND, 30);

            // 头部map
            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("typ", "jwt");
            headerMap.put("alg", "sha256");
            String token = JWT.create().withHeader(headerMap)
//                    .withIssuedAt(Instant.now())
                    .withExpiresAt(instance.getTime())
                    .withClaim("userId", user.getId())
                    .withClaim("username", user.getName())
//                    .withNotBefore(Instant.now())
                    .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(JWT_SECRET));
            return token;
        }
    /**
     * 验证 token
     *
     * @param token
     */
    public static void verification(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        System.out.println(decodedJWT.getClaim("userId"));
        System.out.println(decodedJWT.getClaim("username"));
    }

    public static void main(String[] args) {
//        User user = new User();
//        user.setId(1);
//        user.setName("admin");
//        String token = generateToken(user);
//        System.out.println(token);
        verification("eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MjA1NzU2OTQsInVzZXJJZCI6MSwidXNlcm5hbWUiOiJhZG1pbiJ9.hk9m_ECj15SMKT9kfi112GhH5nmYhMGE89oN0SM4Z64");
    }
}
