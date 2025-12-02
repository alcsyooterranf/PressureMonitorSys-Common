package com.pms.auth.starter.service;

import com.pms.types.Constants;
import com.pms.auth.core.model.UserAggregate;
import com.pms.auth.core.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * JWT服务类
 * 封装JwtUtil，提供Spring集成和LoginUser转换功能*
 * 此类作为Spring Bean，提供与Spring Security集成的JWT操作
 *
 * @author alcsyooterranf
 * @version 1.0
 * @since 2025/11/28
 */
@Slf4j
@Service
public class JwtService {

    private static final String USER_ID = Constants.USER_ID;
    private static final String USER_NAME = Constants.USER_NAME;
    private static final String AUTHORITIES = Constants.AUTHORITIES;

    /**
     * 初始化公钥
     * 由 PublicKeyLoader 在获取公钥文件后调用
     */
    public void initKey() {
        JwtUtil.initKey();
        log.info("JwtService: 公钥初始化完成");
    }

    /**
     * 从token中获取LoginUser对象（Spring Security集成）
     *
     * @param token JWT token
     * @return LoginUser对象
     */
    @SuppressWarnings("unchecked")
    public LoginUser getLoginUserFromToken(String token) {
        Claims claims = JwtUtil.getClaimsFromToken(token);
        
        UserAggregate userAggregate = UserAggregate.builder()
                .id(Long.parseLong(claims.get(USER_ID).toString()))
                .username((String) claims.get(USER_NAME))
                .build();
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<LinkedHashMap<String, String>> list = claims.get(AUTHORITIES, List.class);
        if (list != null) {
            for (LinkedHashMap<String, String> authority : list) {
                authorities.add(new SimpleGrantedAuthority(authority.get("authority")));
            }
        }
        
        return LoginUser.builder()
                .userAggregate(userAggregate)
                .authorities(authorities)
                .build();
    }

    /**
     * 验证token是否有效并判断token是否为refreshToken
     *
     * @param token token
     * @return 若为refreshToken, 则返回其jti; 否则返回null
     */
    public String validateToken(String token) {
        return JwtUtil.validateToken(token);
    }

    /**
     * 从token中获取JTI
     *
     * @param token token
     * @return JTI
     */
    public String getJTIFromToken(String token) {
        return JwtUtil.getJTIFromToken(token);
    }

    /**
     * 从token中获取UserAggregate对象
     *
     * @param token JWT token
     * @return UserAggregate对象
     */
    public UserAggregate getUserAggregateFromToken(String token) {
        return JwtUtil.getUserAggregateFromToken(token);
    }

    /**
     * 从token中获取Claims
     *
     * @param token JWT token
     * @return Claims对象
     */
    public Claims getClaimsFromToken(String token) {
        return JwtUtil.getClaimsFromToken(token);
    }

}

