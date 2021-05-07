package study.templ.infra;

public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}