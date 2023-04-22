package team.studywithme.utils.session;

import org.springframework.stereotype.Component;
import team.studywithme.api.controller.dto.response.KakaoLoginResponse;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    public void createSession(String value , HttpSession httpSession){
        httpSession.setAttribute("session",value);
        httpSession.setMaxInactiveInterval(3600);
    }
}
