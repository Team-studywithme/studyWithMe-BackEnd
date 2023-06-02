package team.studywithme.utils.session;

import org.springframework.stereotype.Component;
import team.studywithme.config.session.SessionProperties;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    public void createSession(Long sessionValue , HttpSession httpSession){
        httpSession.setAttribute(SessionProperties.SESSION, sessionValue);
        httpSession.setMaxInactiveInterval(3600);
    }
}
