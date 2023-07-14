package team.studywithme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.studywithme.config.aop.LogAspect;

@Configuration
public class AspectConfig {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
