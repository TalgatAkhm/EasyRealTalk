package ru.mtl.voidvoice.motion_treker.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leapmotion.leap.Controller;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import ru.mtl.voidvoice.motion_treker.model.MotionVector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements NativeKeyListener {
    public final static String FOLDER_LOCATION =
            "C:\\EasyRealTalk\\motion_treker\\src\\main\\resources\\button_result";

    private Controller controller;

    private WorkerListener workerListener;

    private PrintWriter printWriter;

    private ObjectMapper objectMapper;
    private ObjectMapper avgObjectMapper;
    private int numFrames = 0;

    public Worker() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        workerListener = new WorkerListener();
        controller = new Controller();

        GlobalScreen.addNativeKeyListener(this);
        controller.addListener(workerListener);

        objectMapper = new ObjectMapper();
        avgObjectMapper = new ObjectMapper();
    }

    public void run() {
        String resultFileName = new Date().toString()+".json";
        resultFileName = resultFileName.replaceAll(" ", "_");
        resultFileName = resultFileName.replaceAll(":", "_");
        resultFileName = resultFileName.toLowerCase();
        resultFileName = FOLDER_LOCATION +"/"+ resultFileName;

        try {
            printWriter = new PrintWriter(resultFileName);
            printWriter.print("[\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
                printWriter.print("]");
                printWriter.close();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        } else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
//            printWriter.print(workerListener.showFrameData());
//            printWriter.flush();

            MotionVector motionVector = workerListener.getMotionVector();
            ++numFrames;
            if(numFrames == 2) {
                MotionVector avg = workerListener.getAvg();
                try {
                    String jsonAvg = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(avg);
                    // printWriter.print(jsonAvg);
                    // printWriter.print(",\n");
                    // printWriter.flush();
                    System.out.println(jsonAvg);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            try {
                String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(motionVector);
                printWriter.print(json);
                printWriter.print(",\n");
                printWriter.flush();
               // System.out.println(json);


                System.out.println();
                System.out.println("---//////////////---///////////////////////---/////////////////---//AVG");
               // printWriter.print(",\n");
               // printWriter.print(",\n");
               // printWriter.print(",\n");
               // printWriter.print("---//////////////---///////////////////////---/////////////////---//AVG");
               // printWriter.print(",\n");
               // printWriter.print(",\n");
               // printWriter.print(",\n");
               // printWriter.print(",\n");

                /*String jsonAvg = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(avg);
               // printWriter.print(jsonAvg);
               // printWriter.print(",\n");
               // printWriter.flush();
                System.out.println(jsonAvg);*/
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }/*
            ++numNativeKeyPressed;
            if(numNativeKeyPressed == 2) {
                MotionVector avg = workerListener.getAvg();
                try {
                    System.out.println();
                    System.out.println("---//////////////---///////////////////////---/////////////////---//AVG");
                    String jsonAvg = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(avg);
                    printWriter.print(json);
                    printWriter.print(",\n");
                    printWriter.flush();
                    System.out.println(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            }*/
        }
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
