package il.co.orgo.orgo.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyApplicationListener {


    private final Environment environment;

    @Autowired
    public MyApplicationListener(Environment environment) {
        this.environment = environment;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("--> SPRING.DATASOURCE.URL: {}" , environment.getProperty("spring.datasource.url"));
        log.info("--> SPRING.DATASOURCE.USERNAME: {}" , environment.getProperty("spring.datasource.username"));
    }
}
