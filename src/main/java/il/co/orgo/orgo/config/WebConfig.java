package il.co.orgo.orgo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            //TODO should take host from property file
            registry.addMapping("/**")
                    .allowedOrigins("https://ordo-ui.herokuapp.com")
                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
        }
    }
