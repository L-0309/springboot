package com.liu.springboot.config.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.pojo.User;
import com.liu.springboot.service.UserService;
import com.liu.springboot.utils.Constants;
import com.liu.springboot.utils.VerifyUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-18  10:58
 * @Description: 验证用户是否合法
 * @Author: LiuHaoYu
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //执行认证
        if (!VerifyUtil.verifyStr(token)) {
            throw new ServiceException(Constants.CODE_401,"无token，请重新的登录");
        }
        //获取token中的user id
        String userId;

        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new ServiceException(Constants.CODE_401,"token验证失败");
        }

        //根据token中的userId查询数据库
        User user = userService.getById(userId);
        if (user == null){
            throw new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        //用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

        try {
            //验证token
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_401,"token验证失败，请重新登录");
        }
        return true;
    }
}
