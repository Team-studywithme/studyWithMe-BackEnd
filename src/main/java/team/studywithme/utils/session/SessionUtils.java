package team.studywithme.utils.session;

import org.springframework.stereotype.Component;
import team.studywithme.config.session.Session;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    public void createSession(String sessionValue,HttpSession httpSession){
        httpSession.setAttribute("session",sessionValue);
        httpSession.setMaxInactiveInterval(3600);
    }
}
