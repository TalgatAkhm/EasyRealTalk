package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.tree.AverageMotionVectorGenerator;

class WorkerListener extends Listener {
    private AverageMotionVectorGenerator generator;

    @Autowired
    private AverageMotionVectorGenerator averageMotionVectorGenerator;

    @Override
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    @Override
    public void onDisconnect(Controller controller) {
        System.out.println("Disconnected");
    }

    @Override
    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        averageMotionVectorGenerator.addFrame(frame);
    }
}