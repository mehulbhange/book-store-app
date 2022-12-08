package com.bridgelabz.bookstore.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.bookstore.dto.LoginDTO;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {

    private static final String TOKEN_SECRET = "SecretToken";

    /**
     *
     * @param loginDTO - email & password
     * @return token - generated login token
     */
    /*public String generateLoginToken(LoginDTO loginDTO){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("email", loginDTO.getEmail()).withClaim("password",loginDTO.getPassword()).sign(algorithm);
        return token;
    }*/
    public String generateLoginToken(Long userId){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("userId", userId).sign(algorithm);
        return token;
    }

    /**
     *
     * @param token
     * @return
     */
    /*public LoginDTO decodeLoginToken(String token){
        LoginDTO loginDTO = new LoginDTO();
        Verification verification = null;
        verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtverifier=verification.build();
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim email = decodedjwt.getClaim("email");
        Claim password = decodedjwt.getClaim("password");

        loginDTO.setEmail(email.asString());
        loginDTO.setPassword(password.asString());

        return loginDTO;
    }
*/
    public Long decodeLoginToken(String token){
        LoginDTO loginDTO = new LoginDTO();
        Verification verification = null;
        verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtverifier=verification.build();
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim userId = decodedjwt.getClaim("userId");
        return userId.asLong();
    }
}
