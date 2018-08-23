package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;

import java.io.IOException;

public class Worker {
    private final static Logger LOG = LoggerFactory.getLogger(Worker.class);

    public void run() {
        WorkerListener listener = ApplicationContextHolder.getApplicationContext().getBean(WorkerListener.class);
        Controller controller = new Controller();

        LOG.debug("Add WorkerListener to controller");
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.debug("Remove WorkerListener from controller");
        controller.removeListener(listener);
    }
}
