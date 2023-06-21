package team.studywithme.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOriginPatterns("http://localhost:3000")
                .allowCredentials(true);
    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedOriginPatterns("http://localhost:3000")
//                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
//                .exposedHeaders("*")
//                .maxAge(3000);
//    }
}

