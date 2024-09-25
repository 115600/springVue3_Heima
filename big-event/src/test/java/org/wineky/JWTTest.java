package org.wineky;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest{
    //生成JWT代码

    @Test
    void getToken(){
        //键是String类型,值是Object类型
        Map<String,Object> claims = new HashMap<>();
        claims.put("id","1");
        claims.put("username","张三");
        System.out.println(claims);
        String token = JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*3))
                .sign(Algorithm.HMAC256("winkeydesu"));
        System.out.println(token);
    }
    @Test

    public void TestToken(){
        String Token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTcyNTU1NDc1NX0." +
                "utct8bPvBMDjnMOOs9EjSOBks_eDVZ_sCUoz3v7WObo";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("winkeydesu")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(Token);//验证Token，生成一个解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));


    }
}
