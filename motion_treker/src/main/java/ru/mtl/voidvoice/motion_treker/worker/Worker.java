package ru.mtl.voidvoice.motion_treker.worker;

import com.leapmotion.leap.Controller;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements NativeKeyListener {

    private Controller controller;

    private WorkerListener workerListener;

    public Worker() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        workerListener = new WorkerListener();
        controller = new Controller();

        GlobalScreen.addNativeKeyListener(this);
        controller.addListener(workerListener);
    }

    public void run() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                controller.removeListener(workerListener);
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            workerListener.showFrameData();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
