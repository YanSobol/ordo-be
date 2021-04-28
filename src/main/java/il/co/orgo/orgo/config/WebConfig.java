package il.co.orgo.orgo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

        @Value("${ui.host}")
        private String uiHost;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            System.out.println(uiHost);
            registry.addMapping("/**")
                    .allowedOrigins(uiHost)
                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
        }
    }
