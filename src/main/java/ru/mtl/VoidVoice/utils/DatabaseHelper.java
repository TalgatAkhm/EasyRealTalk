package ru.mtl.VoidVoice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mtl.VoidVoice.dao.GestureDao;
import ru.mtl.VoidVoice.dao.MotionVectorDao;
import ru.mtl.VoidVoice.model.Finger;
import ru.mtl.VoidVoice.model.FingerType;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.Hand;
import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.model.Point3d;
import ru.mtl.VoidVoice.model.Vector3d;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String FOLDER_LOCATION =
            "/Users/mac/projects/SignLanguageLeapMotion/EasyRealTalk/motion_treker/src/main/resources/button_result/";

    private GestureDao gestureDao;

    private MotionVectorDao motionVectorDao;

    private ObjectMapper mapper;

    public DatabaseHelper() {
        gestureDao = ApplicationContextHolder.getApplicationContext().getBean(GestureDao.class);
        motionVectorDao = ApplicationContextHolder.getApplicationContext().getBean(MotionVectorDao.class);

        mapper = new ObjectMapper();

//        insertHardcodeData("Привет");
    }

    public void insertHardcodeData(String gestureName, String fileName) {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(FOLDER_LOCATION+"/"+fileName));
            List<MotionVector> motionVectors = mapper.readValue(jsonData, mapper.getTypeFactory()
                    .constructCollectionType(List.class, MotionVector.class));

            Gesture gesture = new Gesture();
            gesture.setKeyPointList(new ArrayList<>());
            gesture.setMeaning(gestureName);
            for (MotionVector motionVector : motionVectors) {
                KeyPoint keyPoint = new KeyPoint();
                keyPoint.setBaseVector(motionVector);
                gesture.getKeyPointList().add(keyPoint);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
