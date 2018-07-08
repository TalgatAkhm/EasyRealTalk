package ru.mtl.VoidVoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class MainApplication {
    private final static Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    private static MainApplication ourInstance;

    private static ApplicationContext applicationContext;

    private MainApplication() {
        LOG.debug("Create application context");
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        LOG.debug("Creating MainApplication");
        ourInstance = new MainApplication();

//        new Worker().run();
    }

    public static MainApplication getInstance() {
        return ourInstance;
    }

    @Transactional
    public ApplicationContext getSpringContext() {
        return applicationContext;
    }

}
