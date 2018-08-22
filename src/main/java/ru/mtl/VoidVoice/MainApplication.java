package ru.mtl.VoidVoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mtl.VoidVoice.worker.Worker;

public class MainApplication {
    private final static Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    private static MainApplication ourInstance;

    private MainApplication() {
        LOG.debug("Create application context");
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        LOG.debug("Creating MainApplication");
        ourInstance = new MainApplication();

//        DatabaseHelper databaseHelper = ApplicationContextHolder.getApplicationContext().getBean(DatabaseHelper.class);
//        databaseHelper.insertHardcodeData("Привет", "current.json");

        // new Worker().run2();
        new Worker().run3();
    }

    public static MainApplication getInstance() {
        return ourInstance;
    }

}
