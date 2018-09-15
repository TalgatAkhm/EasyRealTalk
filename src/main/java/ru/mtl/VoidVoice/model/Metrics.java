package ru.mtl.VoidVoice.model;


import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import ru.mtl.VoidVoice.tree.GestureDefiner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Metrics {
    private static final Logger LOG = LoggerFactory.getLogger(Metrics.class);
//    private static final Marker EXTEND_INFO = MarkerFactory.getMarker("EXTEND_INFO");

    private static int countOfVectorsToReturn;

    public static int getNearestVectorByEuclid(List<MainVector> listOfVectors, MainVector vector) {
        if (vector.getType().equals(MainVectorType.OnlyRightHand)) {
            return pseudoGetNearestVectorByEuclid(listOfVectors, vector, MainVectorType.OnlyRightHand);
        } else if (vector.getType().equals(MainVectorType.OnlyLeftHand)) {
            return pseudoGetNearestVectorByEuclid(listOfVectors, vector, MainVectorType.OnlyLeftHand);
        } else {
            return pseudoGetNearestVectorByEuclid(listOfVectors, vector, MainVectorType.BothHands);
        }
    }

    public static List<Integer> getNearestVectors(List<MainVector> listOfVectors, MainVector vector, int countOfVectors) {
        countOfVectorsToReturn = countOfVectors;

        if (vector.getType().equals(MainVectorType.OnlyRightHand)) {
            return getNearestVectorListByEuclid(listOfVectors, vector, MainVectorType.OnlyRightHand);
        } else if (vector.getType().equals(MainVectorType.OnlyLeftHand)) {
            return getNearestVectorListByEuclid(listOfVectors, vector, MainVectorType.OnlyLeftHand);
        }

        return getNearestVectorListByEuclid(listOfVectors, vector, MainVectorType.BothHands);
    }

    private static List<Integer> getNearestVectorListByEuclid(List<MainVector> listOfVectors, MainVector vector, MainVectorType mainVectorType) {
        List<MainVector> handList = new ArrayList<>();
        for (MainVector mainVector : listOfVectors) {
            if (mainVector.getType().equals(mainVectorType)) {
                handList.add(mainVector);
            }
        }
        // создаю массив в который потом запишу расстояния между каждым вектором из listOfVectors
        // и vector(схожий которому я ищу).
        List<Double> distances = getDistancesList(handList, vector);
        // возвращаю элемент listOfVectors с индексом равным индексу минимального элемента из distances(ближайший вектор).
        return findMinIndexList(distances);
    }

    /**
     * This method finds the {@link Metrics#countOfVectorsToReturn} indexes
     * of the nearest vectors which are similar to the mainVector
     * @param distances the list of the distances in the list
     * @return list of the indexes of the nearest vectors
     */
    private static List<Integer> findMinIndexList(List<Double> distances) {
        List<Pair<Integer, Double>> resultList = new ArrayList<>();

        for (int i = 0; i < distances.size(); i++) {
            resultList.add(new Pair<>(i, distances.get(i)));
        }

        resultList.sort((o1, o2) -> {
            if (o1.getValue() > o2.getValue())
                return 1;
            else if (o1.getValue() < o2.getValue())
                return -1;
            else
                return 0;
        } );

        return subList(resultList, 0, countOfVectorsToReturn).stream()
                .map(Pair::getKey)
                .collect(Collectors.toList());
    }

    private static <T> List<T> subList(List<T> list, int indexFrom, int indexTo) {
        if (indexTo >= list.size()) {
            return list;
        }

        return list.subList(indexFrom, indexTo);
    }

    //    private static MainVector pseudoGetNearestVectorByEuclid(List<MainVector> listOfVectors, MainVector vector, MainVectorType mainVectorType) {
//        List<MainVector> handList = new ArrayList<>();
//        for (MainVector mainVector :
//                listOfVectors) {
//            if (mainVector.getType().equals(mainVectorType)) {
//                handList.add(mainVector);
//            }
//        }
//        // создаю массив в который потом запишу расстояния между каждым вектором из listOfVectors
//        // и vector(схожий которому я ищу).
//        ArrayList<Double> distances = new ArrayList<Double>();
//        for (MainVector mainVector :
//                handList) {
//            // i-й элемент distances это корень из суммы квадратов разностей соответствующих координат i-го вектора из
//            // listOfVectors и vector.
//            double squareSumm = 0;
//            for (int i = 0; i < mainVector.getVectorNd().size(); i++) {
//                squareSumm += Math.pow(mainVector.getVectorNd().get(i) - vector.getVectorNd().get(i), 2);
//            }
//            distances.add(Math.sqrt(squareSumm));
//        }
//        // возвращаю элемент listOfVectors с индексом равным индексу минимального элемента из distances(ближайший вектор).
//        return listOfVectors.get(findMinIndex(distances));
//    }
    private static int pseudoGetNearestVectorByEuclid(List<MainVector> listOfVectors, MainVector vector, MainVectorType mainVectorType) {
        List<MainVector> handList = new ArrayList<>();
        for (MainVector mainVector :
                listOfVectors) {
            if (mainVector.getType().equals(mainVectorType)) {
                handList.add(mainVector);
            }
        }
        // создаю массив в который потом запишу расстояния между каждым вектором из listOfVectors
        // и vector(схожий которому я ищу).
        List<Double> distances = getDistancesList(handList, vector);
        // возвращаю элемент listOfVectors с индексом равным индексу минимального элемента из distances(ближайший вектор).
        return findMinIndex(distances);
    }

    private static List<Double> getDistancesList(List<MainVector> handList, MainVector vector) {
        List<Double> distances = new ArrayList<>();
        for (MainVector mainVector :
                handList) {
            // i-й элемент distances это корень из суммы квадратов разностей соответствующих координат i-го вектора из
            // listOfVectors и vector.
            double squareSumm = 0;
            for (int i = 0; i < mainVector.getVectorNd().size(); i++) {
                if (!mainVector.getVectorNd().get(i).isNaN() && !vector.getVectorNd().get(i).isNaN()) {
                    squareSumm += Math.pow(mainVector.getVectorNd().get(i) - vector.getVectorNd().get(i), 2);
                }
            }

            distances.add(Math.sqrt(squareSumm));
        }

        return distances;
    }

    private final static double MAX_NORM_FOR_ONE_HAND = Math.sqrt(10);
    private final static double MAX_NORM_FOR_TWO_HANDS = Math.sqrt(45);

    public static MainVector mixedMetrics(List<MainVector> listOfVectors, MainVector vector) {
        if (vector.getType().equals(MainVectorType.OnlyLeftHand)) {
            List<MainVector> leftHandsList = new ArrayList();
            for (MainVector mainVector :
                    listOfVectors) {
                if (mainVector.getType().equals(MainVectorType.OnlyLeftHand)) {
                    leftHandsList.add(mainVector);
                }
            }
            // для каждого элемента из leftHandList создаем List<Double> в котором первый элемент
            // это эвклидова норма между touchList-ами данного vector и вектора из списка. Все последующие
            // элементы от 0 до 1, косинусные расстояния (cosDist) между всеми векторными величинами(чем ближе к нулю,
            // тем ближе). После суммируем все элементы(возможно с некоторыми весами) получившегося списка.
            // Список из получившихся сумм сортируем и минимальный элемент соответствует ближайшему
            // mainVector-у.
            List<Double> mixedMetricsSumList = new ArrayList<>();
            for (MainVector leftMainVector :
                    leftHandsList) {
                List<Double> listOfMixedMetrics = new ArrayList<>();
                listOfMixedMetrics.add(euclidDist(leftMainVector.getTouchListVector(), vector.getTouchListVector())/MAX_NORM_FOR_ONE_HAND);

                listOfMixedMetrics.add(cosDist(leftMainVector.getLeftHand().getPalmDirectionVector(), vector.getLeftHand().getPalmDirectionVector()));
                listOfMixedMetrics.add(cosDist(leftMainVector.getLeftHand().getPalmNormalVector(), vector.getLeftHand().getPalmNormalVector()));
                listOfMixedMetrics.add(cosDist(leftMainVector.getLeftHand().getPalmVelocity(), vector.getLeftHand().getPalmVelocity()));

                for (int i = 0; i < leftMainVector.getLeftFingerList().size(); i++) {
                    listOfMixedMetrics.add(cosDist(leftMainVector.getLeftFingerList().get(i).getFingerDirectionVector(), vector.getLeftFingerList().get(i).getFingerDirectionVector()));
                }

                double sum = 0;
                for (Double d :
                        listOfMixedMetrics) {
                    sum += d;
                }
                mixedMetricsSumList.add(sum);
            }
            return listOfVectors.get(listOfVectors.indexOf(leftHandsList.get(findMinIndex(mixedMetricsSumList))));//доделать
        } else if (vector.getType().equals(MainVectorType.OnlyRightHand)) {
            List<MainVector> rightHandsList = new ArrayList<>();
            for (MainVector mainVector :
                    listOfVectors) {
                if (mainVector.getType().equals(MainVectorType.OnlyRightHand)) {
                    rightHandsList.add(mainVector);
                }
            }
            List<Double> mixedMetricsSumList = new ArrayList<>();
            for (MainVector rightMainVector :
                    rightHandsList) {
                List<Double> listOfMixedMetrics = new ArrayList<>();
                listOfMixedMetrics.add(euclidDist(rightMainVector.getTouchListVector(), vector.getTouchListVector())/MAX_NORM_FOR_ONE_HAND);

                listOfMixedMetrics.add(cosDist(rightMainVector.getRightHand().getPalmDirectionVector(), vector.getRightHand().getPalmDirectionVector()));
                listOfMixedMetrics.add(cosDist(rightMainVector.getRightHand().getPalmNormalVector(), vector.getRightHand().getPalmNormalVector()));
                listOfMixedMetrics.add(cosDist(rightMainVector.getRightHand().getPalmVelocity(), vector.getRightHand().getPalmVelocity()));

                for (int i = 0; i < rightMainVector.getRightFingerList().size(); i++) {
                    listOfMixedMetrics.add(cosDist(rightMainVector.getRightFingerList().get(i).getFingerDirectionVector(), vector.getRightFingerList().get(i).getFingerDirectionVector()));
                }

                double sum = 0;
                for (Double d :
                        listOfMixedMetrics) {
                    sum += d;
                }
                mixedMetricsSumList.add(sum);
            }
            return listOfVectors.get(listOfVectors.indexOf(rightHandsList.get(findMinIndex(mixedMetricsSumList))));
        } else {
            List<MainVector> bothHandsList = new ArrayList<>();
            for (MainVector mainVector :
                    listOfVectors) {
                if (mainVector.getType().equals(MainVectorType.BothHands)) {
                    bothHandsList.add(mainVector);
                }
            }
            List<Double> mixedMetricsSumList = new ArrayList<>();
            for (MainVector bothMainVector :
                    bothHandsList) {
                List<Double> listOfMixedMetrics = new ArrayList<>();
                listOfMixedMetrics.add(euclidDist(bothMainVector.getTouchListVector(), vector.getTouchListVector())/MAX_NORM_FOR_TWO_HANDS);

                listOfMixedMetrics.add(cosDist(bothMainVector.getLeftHand().getPalmDirectionVector(), vector.getLeftHand().getPalmDirectionVector()));
                listOfMixedMetrics.add(cosDist(bothMainVector.getLeftHand().getPalmNormalVector(), vector.getLeftHand().getPalmNormalVector()));
                listOfMixedMetrics.add(cosDist(bothMainVector.getLeftHand().getPalmVelocity(), vector.getLeftHand().getPalmVelocity()));

                for (int i = 0; i < bothMainVector.getLeftFingerList().size(); i++) {
                    listOfMixedMetrics.add(cosDist(bothMainVector.getLeftFingerList().get(i).getFingerDirectionVector(), vector.getLeftFingerList().get(i).getFingerDirectionVector()));
                }

                listOfMixedMetrics.add(cosDist(bothMainVector.getRightHand().getPalmDirectionVector(), vector.getRightHand().getPalmDirectionVector()));
                listOfMixedMetrics.add(cosDist(bothMainVector.getRightHand().getPalmNormalVector(), vector.getRightHand().getPalmNormalVector()));
                listOfMixedMetrics.add(cosDist(bothMainVector.getRightHand().getPalmVelocity(), vector.getRightHand().getPalmVelocity()));

                for (int i = 0; i < bothMainVector.getRightFingerList().size(); i++) {
                    listOfMixedMetrics.add(cosDist(bothMainVector.getRightFingerList().get(i).getFingerDirectionVector(), vector.getRightFingerList().get(i).getFingerDirectionVector()));
                }
                double sum = 0;
                for (Double d :
                        listOfMixedMetrics) {
                    sum += d;
                }
                mixedMetricsSumList.add(sum);
            }
            return listOfVectors.get(listOfVectors.indexOf(bothHandsList.get(findMinIndex(mixedMetricsSumList))));
        }
    }

    public static MainVector getNearestVectorByCosin(List<MainVector> mainVectorList, MainVector vector) {
        if (mainVectorList.get(0).getVectorNd().size() == vector.getVectorNd().size()) {
            ArrayList<Double> distances = new ArrayList<Double>();
            for (MainVector mainVector :
                    mainVectorList) {
                distances.add(nDimDotProduct(mainVector, vector) / Math.sqrt(nDimDotProduct(mainVector, mainVector)) / Math.sqrt(nDimDotProduct(vector, vector)));
            }
            return mainVectorList.get(findMaxIndex(distances));
        } else {
            throw new IllegalArgumentException("vector should be of same dimension with vectors in list");
        }
    }

    public static Double cosDist(Vector3d vector1, Vector3d vector2) {
        return 1 - Vector3d.scalarProduct(vector1, vector2) / (Vector3d.module(vector1) * Vector3d.module(vector2));
    }

    public static Double euclidDist(List<Double> list1, List<Double> list2) {
        double sum = 0;
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("two lists are not the same dimension");
        } else {
            for (int i = 0; i < list1.size(); i++) {
                sum += Math.pow(list1.get(i) - list2.get(i), 2);
            }
        }
        return Math.sqrt(sum);
    }

    public static int findMinIndex(List<Double> arrayList) {
        int indexOfMin = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            LOG.debug(arrayList.get(i).toString());
            if (arrayList.get(i) < arrayList.get(indexOfMin)) {
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    public static int findMaxIndex(List<Double> arrayList) {
        int indexOfMin = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) > arrayList.get(indexOfMin)) {
                indexOfMin = i;
            }
        }
        return indexOfMin;
    }

    public static double nDimDotProduct(MainVector mainVector1, MainVector mainVector2) {
        double summ = 0;
        for (int i = 0; i < mainVector1.getVectorNd().size(); i++) {
            summ += mainVector1.getVectorNd().get(i) * mainVector2.getVectorNd().get(i);
        }
        return summ;
    }
}


