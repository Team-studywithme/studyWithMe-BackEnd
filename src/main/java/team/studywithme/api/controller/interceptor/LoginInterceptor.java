package team.studywithme.api.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import team.studywithme.config.session.SessionProperties;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(SessionProperties.SESSION) == null){
            throw new AuthenticationException("세션값이 없습니다.");
        }

        return true;
    }

}