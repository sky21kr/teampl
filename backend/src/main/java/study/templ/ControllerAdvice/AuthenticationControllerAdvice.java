package study.templ.ControllerAdvice;

import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import study.templ.infra.AuthenticationFailedException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AuthenticationControllerAdvice {

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseBody
    public AuthenticationFailedResponse handle(AuthenticationFailedException e, HttpServletRequest request) {
        return new AuthenticationFailedResponse();
    }

    @Getter
    class AuthenticationFailedResponse {
        private final String message = "Error: no authentication";
    }
}