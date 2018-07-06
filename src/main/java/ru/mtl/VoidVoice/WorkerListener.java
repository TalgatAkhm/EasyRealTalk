package ru.mtl.VoidVoice; /******************************************************************************\
 * Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
 * Leap Motion proprietary and confidential. Not for distribution.              *
 * Use subject to the terms of the Leap Motion SDK Agreement available at       *
 * https://developer.leapmotion.com/sdk_agreement, or another agreement         *
 * between Leap Motion and you, your company or other organization.             *
 \******************************************************************************/

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class WorkerListener extends Listener {
    private long timeStamp = -1;
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        if (frame.timestamp() < timeStamp + Math.pow(10, 6)) {
            return;
        }

        //Get hands
        for(Hand hand : frame.hands()) {
            String handType = hand.isLeft() ? "Left hand" : "Right hand";

            // Get the hand's normal vector and direction
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();

            // Get arm bone
            Arm arm = hand.arm();

            // Get fingers
            for (Finger finger : hand.fingers()) {
                if(finger.type().name().equals("TYPE_PINKY")) {
                    System.out.println("FINGER TYPE: " +finger.type() + "      " + finger.direction().getX() + "      " + finger.direction().getY() + "      " + finger.direction().getZ());
                }
                //Get Bones
                for(Bone.Type boneType : Bone.Type.values()) {
                    Bone bone = finger.bone(boneType);
                }
            }
        }

        // Get tools
        for(Tool tool : frame.tools()) {
            System.out.println("TOOL");
        }

        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);

            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    boolean clockWise = false;
                    CircleGesture circle = new CircleGesture(gesture);

                    // Calculate clock direction using the angle between circle normal and pointable
                    String clockwiseness;
                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2) {
                        // Clockwise if angle is less than 90 degrees
                        clockwiseness = "clockwise";
                        clockWise = true;
                    } else {
                        clockwiseness = "counterclockwise";
                    }

                    // Calculate angle swept since last frame
                    double sweptAngle = 0;
                    if (circle.state() != State.STATE_START) {
                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                        if (!clockWise) {
                            System.out.println("Повторите, пожалуйста");
                            timeStamp = frame.timestamp();

                            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
                            out.println("some-utf8-string");
                        }
                    }

//                    System.out.println("CIRCLE " + clockwiseness);
                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    float angle = swipe.direction().angleTo(Vector.xAxis());
                    if (angle < Math.PI / 3 || angle > 2 * Math.PI / 3) {
                        if (swipe.startPosition().getX() < swipe.position().getX()) {
                            System.out.println("Правее");
                        } else {
                            System.out.println("Левее");
                        }
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
                        out.println("some-utf8-string");
                    } else {
                        if (swipe.startPosition().getY() < swipe.position().getY()) {
                            System.out.println("Выше");
                        } else {
                            System.out.println("Ниже");
                        }
                    }

                    timeStamp = frame.timestamp();
                    break;
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    System.out.println("SCREEN TAP");
                    break;
                case TYPE_KEY_TAP:
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    System.out.println("KEY TAP");
                    break;
                default:
                    System.out.println("Unknown gesture type.");
                    break;
            }
        }

        if (!frame.hands().isEmpty() || !gestures.isEmpty()) {
//            System.out.println();
        }
    }
}