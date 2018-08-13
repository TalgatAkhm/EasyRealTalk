package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ru.mtl.VoidVoice.dao.GestureDao;
import ru.mtl.VoidVoice.dao.MotionDao;
import ru.mtl.VoidVoice.dao.MotionVectorDao;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionType;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.tree.GestureTree;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;
import ru.mtl.VoidVoice.utils.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private final static Logger LOG = LoggerFactory.getLogger(Worker.class);

    private MotionDao motionDao;
    private MotionVectorDao motionVectorDao;

    private DatabaseHelper databaseHelper;

    public void run() {
        //TODO::Creating motion vector tree
        // Create a sample listener and controller
        WorkerListener listener = ApplicationContextHolder.getApplicationContext().getBean(WorkerListener.class);
        Controller controller = new Controller();

//        databaseHelper = ApplicationContextHolder.getApplicationContext().getBean(DatabaseHelper.class);
//        databaseHelper.insertHardcodeData("Привет", "current.json");
        GestureTree gestureTree = ApplicationContextHolder.getApplicationContext().getBean(GestureTree.class);
        System.out.println(gestureTree.drawTree());
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

        GestureDao gestureDao = ApplicationContextHolder.getApplicationContext().getBean(GestureDao.class);
        List<Gesture> list = gestureDao.getAll();
    }

    public void run2() {
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
    }
}
