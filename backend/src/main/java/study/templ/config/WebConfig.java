package study.templ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.templ.intercepter.BasicIntercepter;
import study.templ.intercepter.JwtAuthIntercepter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final List<String> tokenRequirements = Arrays.asList(
            "/test1",
            "/test3");

    @Bean
    JwtAuthIntercepter getJwtAuthIntercepter() {
        return new JwtAuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration reg = registry.addInterceptor(getJwtAuthIntercepter());

        for (String pattern : tokenRequirements) {
            reg.addPathPatterns(pattern);
        }
    }
}
