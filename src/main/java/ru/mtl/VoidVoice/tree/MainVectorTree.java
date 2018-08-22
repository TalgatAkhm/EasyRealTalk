package ru.mtl.VoidVoice.tree;

import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.model.MainVector;
import ru.mtl.VoidVoice.model.Metrics;

import java.util.ArrayList;
import java.util.List;

public class MainVectorTree {

    private List<List<MainVector>> mainVectorLayersList;

    public MainVectorTree() {
            mainVectorLayersList = new ArrayList<>();
        for (List<KeyPoint> treeNodeList : GestureTree.getTree()) {
            List<MainVector> mainVectorList = new ArrayList<>();
            for (KeyPoint treeNode : treeNodeList) {
                mainVectorList.add(new MainVector(treeNode.getBaseVector()));
            }
            mainVectorLayersList.add(mainVectorList);
        }
    }

    public Object[] getLayerVector(MainVector mainVector, int layerIndex){
        // возвращаю массив [MainVector, int], его первый элемент - вектор из layerIndex-го слоя,
        // наиболее похожий на данный вектор, второй элемент - индекс найденного вектора в своем слое.
        Object[] result = new Object[2];
        result[0] = Metrics.getNearestVectorByEuclid(mainVectorLayersList.get(layerIndex), mainVector);
        result[1] = mainVectorLayersList.indexOf(result[0]);
        return result;
    }
}
