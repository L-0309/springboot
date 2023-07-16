package com.liu.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.liu.springboot.pojo.User;
import com.liu.springboot.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-17  15:34
 * @Description: token工具类
 * @Author: LiuHaoYu
 */
@Component
public class TokenUtils {
    private static UserService staticUserService;

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    public static String genToken(String userId, String sign) {
        // 将user id 保存到token里面，作为载荷
        return JWT.create().withAudience(userId)
                // 2小时后过期
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                //以password作为 token 密钥
                .sign(Algorithm.HMAC256(sign));
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return user
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (VerifyUtil.verifyStr(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(userId);
            }else {
                return null;
            }
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
