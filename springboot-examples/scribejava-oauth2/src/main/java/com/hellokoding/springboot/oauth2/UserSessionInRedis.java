package com.hellokoding.springboot.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserSessionInRedis {
    private final RedisTemplate<String, Object> redisTemplate;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    public void put(String key, Object value, Duration timeout) {
        redisTemplate.opsForValue().set(buildSessionKey(key), value, timeout);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(buildSessionKey(key));
    }

    public void invalidate() {
        Set<String> keys = redisTemplate.keys(httpServletRequest.getSession().getId().concat("*"));
        for (String key : keys) {
            redisTemplate.expire(key, Duration.ZERO);
        }

        Cookie sessionCookie = WebUtils.getCookie(httpServletRequest, "JSESSIONID");
        sessionCookie.setMaxAge(0);
        httpServletResponse.addCookie(sessionCookie);

        httpServletRequest.getSession().invalidate();
    }

    private String buildSessionKey(String key) {
        return String.format("%s-%s", httpServletRequest.getSession().getId(), key);
    }
}
