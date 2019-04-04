package org.wz.config.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @project: userMaster
 * @package: org.wz.config.utils
 * @author: Administrator
 * @crDate: 2019/3/26 15:25
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class JwtUtil {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 60 * 1000;

    /**
     * token 私钥
     */
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e720a6a0e618";

    /**
     * 生成签名，1分钟后过期
     * @param account
     * @param password
     * @return
     */
    public static String sign(String account,String password){
        try {
            //设置过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);

            //私钥以及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            //设置头部信息
            Map<String,Object> header = new HashMap<>(2);
            header.put("type","JWT");
            header.put("alg","HS256");
            //附带account，password信息，生成签名
            return JWT.create().withHeader(header).withClaim("userPassword",password).withClaim("userAccount",account).withExpiresAt(date).sign(algorithm);
        }catch (UnsupportedEncodingException e){
            return  null;
        }
    }

    /**
     * 校验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
