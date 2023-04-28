package team.studywithme.utils.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    public void createSession(Long sessionValue , HttpSession httpSession){
        httpSession.setAttribute("session", sessionValue);
        httpSession.setMaxInactiveInterval(3600);
    }
}
