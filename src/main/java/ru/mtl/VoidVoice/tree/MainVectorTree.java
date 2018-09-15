package ru.mtl.VoidVoice.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.model.MainVector;
import ru.mtl.VoidVoice.model.Metrics;
import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.utils.ApplicationContextHolder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MainVectorTree {
    private static final Logger LOG = LoggerFactory.getLogger(MainVectorTree.class);

    private List<List<MainVector>> mainVectorLayersList;

    @Resource
    private GestureTree gestureTree;

    // the count of vectors which would be return
    private int countOfVectors;

    public MainVectorTree(int countOfVectors) {
        this.countOfVectors = countOfVectors;
        mainVectorLayersList = new ArrayList<>();
        gestureTree = ApplicationContextHolder.getApplicationContext().getBean(GestureTree.class);
        for (List<KeyPoint> keyPointList : gestureTree.getTree()) {
            List<MainVector> mainVectorList = new ArrayList<>();
            for (KeyPoint treeNode : keyPointList) {
                mainVectorList.add(new MainVector(treeNode.getBaseVector()));
            }
            mainVectorLayersList.add(mainVectorList);
        }
    }

    public int getLayerVector(MainVector mainVector, int layerIndex){
        // возвращаю массив [MainVector, int], его первый элемент - вектор из layerIndex-го слоя,
        // наиболее похожий на данный вектор, второй элемент - индекс найденного вектора в своем слое.
        return Metrics.getNearestVectorByEuclid(mainVectorLayersList.get(layerIndex), mainVector);
    }

//    public Object[] getLayerVector(MainVector mainVector, int layerIndex){
//        // возвращаю массив [MainVector, int], его первый элемент - вектор из layerIndex-го слоя,
//        // наиболее похожий на данный вектор, второй элемент - индекс найденного вектора в своем слое.
//        Object[] result = new Object[2];
//        result[0] = Metrics.getNearestVectorByEuclid(mainVectorLayersList.get(layerIndex), mainVector);
//        result[1] = mainVectorLayersList.indexOf(result[0]);
//        return result;
//    }

    public int getNearestLayerInList(MainVector mainVector, List<MotionVector> motionVectorList) {
        List<MainVector> mainVectorList = new ArrayList<>();
        for (MotionVector motionVector : motionVectorList) {
            mainVectorList.add(new MainVector(motionVector));
        }

        return Metrics.getNearestVectorByEuclid(mainVectorList, mainVector);
    }
//    public Object[] getNearestLayerInList(MainVector mainVector, List<MotionVector> motionVectorList) {
//        List<MainVector> mainVectorList = new ArrayList<>();
//        for (MotionVector motionVector : motionVectorList) {
//            mainVectorList.add(new MainVector(motionVector));
//        }
//
//        Object[] result = new Object[2];
//        result[0] = Metrics.getNearestVectorByEuclid(mainVectorList, mainVector);
//        result[1] = mainVectorLayersList.indexOf(result[0]);
//        return result;
//    }

    public List<Integer> getNearestIndexesInLayer(MainVector mainVector, int layerIndex) {
        //TODO::check if mainvectortree is not zero
        return Metrics.getNearestVectors(mainVectorLayersList.get(layerIndex), mainVector, countOfVectors);
    }

    public List<Integer> getNearestIndexesInList(MainVector mainVector, List<MotionVector> motionVectorList) {
        List<MainVector> mainVectorList = new ArrayList<>();
        for (MotionVector motionVector : motionVectorList) {
            mainVectorList.add(new MainVector(motionVector));
        }

        return Metrics.getNearestVectors(mainVectorList, mainVector, countOfVectors);
    }


}
