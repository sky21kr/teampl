package study.templ.domain;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class CheckAuthority {

    public String checkLogin(HttpSession httpSession){
        String loginId =(String) httpSession.getAttribute("loginId");
        if(loginId==null){
            throw new RuntimeException("Wrong access_Authority session information does not exist");
        }
        return loginId;
    }

    public String checkAuthority(HttpSession httpSession){
        String authority =(String) httpSession.getAttribute("authority");
        if(authority==null){
            throw new RuntimeException("Wrong access_Authority session information does not exist");

        }
        return authority;
    }

    public boolean checkOwner(HttpSession httpSession, int ownerId){
        String loginId = checkLogin(httpSession);
        if(!loginId.equals(ownerId)){
            return false;
        }
        return true;
    }
}
