/**
  * 一个 JWT 实际上就是一个字符串，它由三部分组成，头部、载荷与签名。前两部分需要经过 Base64 编码，后一部分通过前两部分 Base64 编码后再加密而成。
  *  jwt标准中注册的声明（建议但不强制使用）
  *         iss: jwt签发者
  *         sub: jwt所面向的用户
  *         aud: 接收jwt的一方
  *         exp: jwt的过期时间，这个过期时间必须要大于签发时间
  *         nbf: 定义在什么时间之前，该jwt都是不可用的.
  *         iat: jwt的签发时间
  *         jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
  *
  */



package com.roden.blog.api.service;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@Log
public class JwtService {
    /**
     * jwt 加密解密密钥
     */
    private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    /**
     * jwt签发者
     */
    private static final String JWT_ISSUER = "LD";

    /**
     * jwt解密，需要密钥和token，如果解密失败，说明token无效
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET)).build()
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (JwtException ex) {
            return null;
        }
    }

    /**
     * 创建token
     *
     * @param map 主题，也差不多是个人的一些信息，为了好的移植，采用了map放个人信息，而没有采用JSON
     * @param audience 发送谁
     * @param issuer 个人签名
     * @param jwtId 相当于jwt的主键,不能重复
     * @param TTLMillis Token过期时间
     * @param base64Security 生成签名密钥
     * @return
     */
    public static String createJWT(Map map, String audience,long TTLMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setIssuedAt(now)
                .setSubject(map.toString())
                .setIssuer(JWT_ISSUER)
                //.setId(jwtId)
                .setAudience(audience)
                .signWith(signingKey, signatureAlgorithm);  //设置签名使用的签名算法和签名使用的秘钥
        //添加Token过期时间
        if (TTLMillis >= 0) {
            // 过期时间
            long expMillis = nowMillis + TTLMillis;
            // 现在是什么时间
            Date exp = new Date(expMillis);
            // 系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }
}
