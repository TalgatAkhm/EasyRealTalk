package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mtl.VoidVoice.dao.MotionDao;
import ru.mtl.VoidVoice.dao.MotionVectorDao;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionType;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private final static Logger LOG = LoggerFactory.getLogger(Worker.class);

    private MotionDao motionDao;
    private MotionVectorDao motionVectorDao;

    public static void main(String[] args) {
        //TODO::Creating motion vector tree
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
        motionDao = ApplicationContextHolder.getApplicationContext().getBean(MotionDao.class);
        motionDao.create(new Motion(MotionType.Shake));
        //MotionVectorTouchesConverter testing
        //////////////////////////////////////////////////////////////////////
        motionVectorDao = ApplicationContextHolder.getApplicationContext().getBean(MotionVectorDao.class);
        MotionVector mv = new MotionVector();
        List<List<Integer>> touchList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            touchList.add(new ArrayList<>(10));
            for (int j = 0; j < 10; ++j) {
                touchList.get(i).add(i == j ? 1 : 0);
                System.out.print(touchList.get(i).get(j));
            }
            System.out.println();
        }

        mv.setTouchList(touchList);
        motionVectorDao.create(mv);


        touchList = motionVectorDao.getAll().get(0).getTouchList();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                System.out.print(touchList.get(i).get(j));
            }
            System.out.println();
        }


    }
}
