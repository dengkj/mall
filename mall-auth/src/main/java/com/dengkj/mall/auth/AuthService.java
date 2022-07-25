package com.dengkj.mall.auth;

import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService {

    @Resource
    private JwtUtil jwt;

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public AuthResponse login(AuthRequest authRequest) {
        User user = userService.getOne(authRequest.getUserName());
        if (user != null && user.getPassword().equals(authRequest.getPassword())) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            String accessToken = jwt.generate(userVO, TokenTypeEnum.ACCESS);
            String refreshToken = jwt.generate(userVO, TokenTypeEnum.REFRESH);

            String key = user.getUserId().toString();
            stringRedisTemplate.opsForHash().put(key, TokenTypeEnum.ACCESS.name(), accessToken);
            stringRedisTemplate.opsForHash().put(key, TokenTypeEnum.REFRESH.name(), refreshToken);

            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new RuntimeException("用户不存在或密码错误");
        }
    }

    public AuthResponse refresh(String refreshToken) {
        jwt.validateToken(refreshToken);
        Claims claims = jwt.getClaims(refreshToken);
        UserVO userVO = new UserVO();
        userVO.setEmail((String) claims.get("email"));
        userVO.setUserId((Integer) claims.get("userId"));
        userVO.setAuth(((Integer) claims.get("auth")).byteValue());
        userVO.setUserName((String) claims.get("userName"));
        String accessToken = jwt.generate(userVO, TokenTypeEnum.ACCESS);

        String key = userVO.getUserId().toString();
        stringRedisTemplate.opsForHash().put(key, TokenTypeEnum.ACCESS.name(), accessToken);

        return new AuthResponse(accessToken, refreshToken);
    }

}
