package ru.mtl.voidvoice.motion_treker.worker;

import com.leapmotion.leap.Controller;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RangeWorker implements RangeWorkerListener.FrameReader, NativeKeyListener {

    private Controller controller;

    private RangeWorkerListener rangeWorkerListener;

    private String outputFolder;

    private PrintWriter resultWriter;

    private boolean isRunning = false;

    public RangeWorker(String outputFolder) {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        this.outputFolder = outputFolder;

        String resultFileName = new Date().toString()+".csv";
        resultFileName = resultFileName.replaceAll(" ", "_");
        resultFileName = resultFileName.replaceAll(":", "_");
        resultFileName = resultFileName.toLowerCase();
        resultFileName = outputFolder +"/"+ resultFileName;

        try {
            resultWriter = new PrintWriter(resultFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open result file");
            e.printStackTrace();
        }

        resultWriter.print(RangeWorkerListener.TITLES_FOR_DATA);
        resultWriter.flush();

        rangeWorkerListener = new RangeWorkerListener(this);
        controller = new Controller();

        GlobalScreen.addNativeKeyListener(this);
        controller.addListener(rangeWorkerListener);
    }

    public void run() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());

            System.exit(1);
        }
    }

    public void onFrameRead(String data) {
        resultWriter.print(data);
        resultWriter.flush();
    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                controller.removeListener(rangeWorkerListener);
                GlobalScreen.unregisterNativeHook();
                resultWriter.close();
                System.out.println("Exit");
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            if (!isRunning) {
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isRunning = true;
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
