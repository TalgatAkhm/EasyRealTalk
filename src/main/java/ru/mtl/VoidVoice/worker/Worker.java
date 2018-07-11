package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Controller;
import ru.mtl.VoidVoice.MainApplication;
import ru.mtl.VoidVoice.dao.MotionDao;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionType;

import javax.annotation.Resource;
import java.io.IOException;

public class Worker {

    @Resource
    private MotionDao motionDao;

    public static void main(String[] args) {
        // Create a sample listener and controller
        WorkerListener listener = new WorkerListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }

    public void run() {
        motionDao = MainApplication.getInstance().getSpringContext().getBean(MotionDao.class);
        motionDao.create(new Motion(MotionType.Shake));
    }
}