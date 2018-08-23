package ru.mtl.VoidVoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;
import ru.mtl.VoidVoice.utils.DatabaseHelper;
import ru.mtl.VoidVoice.worker.Worker;

public class MainApplication {
    private final static Logger LOG = LoggerFactory.getLogger(MainApplication.class);

    private final static String FOLDER_LOCATION = "";
    private final static String FILE_NAME = "";

    private static MainApplication ourInstance;

    private MainApplication() {
        LOG.debug("Create application context");
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        LOG.debug("Creating MainApplication");
        ourInstance = new MainApplication();

//        DatabaseHelper databaseHelper = ApplicationContextHolder.getApplicationContext().getBean(DatabaseHelper.class);
//        databaseHelper.insertHardcodeData("Привет", "Привет.json");
//        databaseHelper.insertHardcodeData("Почему", "Почему.json");

        new Worker().run();
    }

    public static MainApplication getInstance() {
        return ourInstance;
    }

}
