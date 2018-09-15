package ru.mtl.VoidVoice.utils;

import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.model.Point3d;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class MotionVectorTouchesConverter implements AttributeConverter<List<List<Double>>, String> {

    private static final int NUMBER_OF_LAYERS = 5;
    private static final int NUMBER_OF_COLUMNS_FOR_ONE_HAND = 5;
    private static final int NUMBER_OF_COLUMNS_FOR_TWO_HANDS = 10;
    private static final int STRING_LENGTH_FOR_ONE_HAND = 15;
    private static final int MAX_AFFORTABLE_DISTANCE = 1;

    @Override
    public String convertToDatabaseColumn(List<List<Double>> matrix) {
        if (matrix != null) {
            if (matrix.get(NUMBER_OF_LAYERS - 1).size() == NUMBER_OF_COLUMNS_FOR_ONE_HAND) {
                StringBuilder matrixToString = new StringBuilder();
                for (int i = 0; i < matrix.size(); i++) {
                    for (int j = 0; j < matrix.get(i).size(); j++) {
                        int value = matrix.get(i).get(j).intValue();
                        matrixToString.append(value == -1 ? 3 : value);
                    }
                }
                return matrixToString.toString();
            } else {
                StringBuilder matrixToString = new StringBuilder();
                for (List<Double> layer :
                        matrix) {
                    for (Double d :
                            layer) {
                        int value = d.intValue();
                        matrixToString.append(value == -1 ? 3 : value);
                    }
                }
                return matrixToString.toString();
            }
        }
        return null;
    }

    @Override
    public List<List<Double>> convertToEntityAttribute(String s) {
        if (s != null) {
            List<List<Double>> matrix = new ArrayList<>();
            if (s.length() == STRING_LENGTH_FOR_ONE_HAND){
                for (int i = 0; i < NUMBER_OF_LAYERS; i++) {
                    for (int j = 0; j < NUMBER_OF_COLUMNS_FOR_ONE_HAND; j++) {
                        int value = Integer.parseInt(s.charAt(i*NUMBER_OF_LAYERS + j)+"");
                        matrix.get(i).add(value == 3 ? -1.0 : (double) value);
                    }
                }
            } else{
                for (int i = 0; i < NUMBER_OF_LAYERS; i++) {
                    for (int j = 0; j < NUMBER_OF_COLUMNS_FOR_TWO_HANDS; j++) {
                        int value = Integer.parseInt(s.charAt(i*NUMBER_OF_LAYERS + j) + "");
                        matrix.get(i).add(value == 3 ? -1.0 : (double) value);
                    }
                }
            }
            return matrix;
        }
        return null;
    }

    public static List<List<Double>> createTouchList(MotionVector motionVector) throws IllegalArgumentException {
        List<List<Double>> layerList = new ArrayList<>();
        //в случае одной руки layerList содержит 5 элементов(touchList). Первый layer содержит один элемент(соприкосновение 1го и 1го пальцев(Pinky)),
        // второй - два эл-та соприкосновения 2го пальца с 1ым и 2ым, и так еще три слоя.

        if (motionVector == null) {
            return layerList;
        }

        if ((motionVector.getRightFingersList() == null && motionVector.getLeftFingersList() == null)) {
            throw new IllegalArgumentException("somehow MotionLeap has sent frame without hands");
        } else if (motionVector.getRightFingersList() != null && motionVector.getLeftFingersList() == null) {
            for (int i = 0; i < motionVector.getRightFingersList().size(); i++) {
                List<Double> touchList = new ArrayList<>();
                for (int j = 0; j < i + 1; j++) {
                    touchList.add(checkTouch(motionVector.getRightFingersList().get(i).getFingerTipPosition(), motionVector.getRightFingersList().get(j).getFingerTipPosition()));
                }
                layerList.add(touchList);
            }
        } else if (motionVector.getLeftFingersList() != null && motionVector.getRightFingersList() == null) {
            for (int i = 0; i < motionVector.getRightFingersList().size(); i++) {
                List<Double> touchList = new ArrayList<>();
                for (int j = 0; j < i + 1; j++) {
                    touchList.add(checkTouch(motionVector.getLeftFingersList().get(i).getFingerTipPosition(), motionVector.getLeftFingersList().get(j).getFingerTipPosition()));
                }
                layerList.add(touchList);
            }
        } else {
            for (int i = 0; i < motionVector.getLeftFingersList().size(); i++) {
                List<Double> touchList = new ArrayList<>();
                for (int j = 0; j < motionVector.getLeftFingersList().size(); j++) {
                    touchList.add(checkTouch(motionVector.getLeftFingersList().get(i).getFingerTipPosition(), motionVector.getLeftFingersList().get(j).getFingerTipPosition()));
                    layerList.add(touchList);
                }
            }

            layerList.get(1).set(0, checkTouch(motionVector.getRightFingersList().get(3).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));
            layerList.get(2).set(0, checkTouch(motionVector.getRightFingersList().get(2).getFingerTipPosition(), motionVector.getRightFingersList().get(3).getFingerTipPosition()));
            layerList.get(2).set(1, checkTouch(motionVector.getRightFingersList().get(2).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));
            layerList.get(3).set(0, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(2).getFingerTipPosition()));
            layerList.get(3).set(1, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(3).getFingerTipPosition()));
            layerList.get(3).set(2, checkTouch(motionVector.getRightFingersList().get(1).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));
            layerList.get(4).set(0, checkTouch(motionVector.getRightFingersList().get(0).getFingerTipPosition(), motionVector.getRightFingersList().get(1).getFingerTipPosition()));
            layerList.get(4).set(1, checkTouch(motionVector.getRightFingersList().get(0).getFingerTipPosition(), motionVector.getRightFingersList().get(2).getFingerTipPosition()));
            layerList.get(4).set(2, checkTouch(motionVector.getRightFingersList().get(0).getFingerTipPosition(), motionVector.getRightFingersList().get(3).getFingerTipPosition()));
            layerList.get(4).set(3, checkTouch(motionVector.getRightFingersList().get(0).getFingerTipPosition(), motionVector.getRightFingersList().get(4).getFingerTipPosition()));

            for (int i = 0; i < motionVector.getLeftFingersList().size(); i++) {
                for (int j = 0; j < motionVector.getRightFingersList().size(); j++) {
                    layerList.get(i).add(checkTouch(motionVector.getLeftFingersList().get(i).getFingerTipPosition(), motionVector.getRightFingersList().get(i).getFingerTipPosition()));
                }
            }
        }
        return layerList;
    }

    private static double checkTouch(Point3d point1, Point3d point2) {
        // если точки совпадают, возвращаем -1, если между ними расстояние меньше MAX_AFFORTABLE_DISTANCE,
        // то возвращаем 1(касание), если больше, то 0.
        if (point1.equals(point2)) {
            return -1.d;
        } else if (point1.distance(point2) < MAX_AFFORTABLE_DISTANCE) {
            return 1.d;
        } else {
            return 0.d;
        }
    }
}
