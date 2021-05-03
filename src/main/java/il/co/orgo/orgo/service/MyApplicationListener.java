package il.co.orgo.orgo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("--> Profiles: {}" ,  environment.getActiveProfiles());
    }
}







//class MyApplicationListener
//        implements ApplicationListener<ApplicationReadyEvent> {
//
//    private static final Logger logger = ...;
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        logger.info("ApplicationListener#onApplicationEvent()");
//    }
//
//}