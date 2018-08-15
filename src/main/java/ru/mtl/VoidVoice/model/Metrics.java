package ru.mtl.VoidVoice.model;



import java.util.*;

public class Metrics {
    public static MainVector getNearestVectorByEuclid(List<MainVector> listOfVectors, MainVector vector) {
        // создаю массив в который потом запишу расстояния между каждым вектором из listOfVectors
        // и vector(схожий которому я ищу).
        if (listOfVectors.get(0).getVectorNd().size() == vector.getVectorNd().size()) {
            ArrayList<Double> distances = new ArrayList<Double>();
            for (MainVector mainVector :
                    listOfVectors) {
                // i-й элемент distances это корень из суммы квадратов разностей соответствующих координат i-го вектора из
                // listOfVectors и vector.
                double squareSumm = 0;
                for (int i = 0; i < mainVector.getVectorNd().size(); i++) {
                    squareSumm += Math.pow(mainVector.getVectorNd().get(i) - vector.getVectorNd().get(i), 2);
                }
                distances.add(Math.sqrt(squareSumm));
            }
            // возвращаю элемент listOfVectors с индексом равным индексу минимального элемента из distances(ближайший вектор).
            return listOfVectors.get(findMinIndex(distances));
        } else {
            throw new IllegalArgumentException("vector should be of same dimension");
        }
    }
    //TODO сделать сравнение с весами по координатам.
    public static MainVector getNearestVectorByCosin(List<MainVector> mainVectorList, MainVector vector) {
        if (mainVectorList.get(0).getVectorNd().size() == vector.getVectorNd().size()) {
            ArrayList<Double> distances = new ArrayList<Double>();
            for (MainVector mainVector :
                    mainVectorList) {
                distances.add(nDimDotProduct(mainVector, vector)/Math.sqrt(nDimDotProduct(mainVector,mainVector))/Math.sqrt(nDimDotProduct(vector,vector)));
            }
            return mainVectorList.get(findMaxIndex(distances));
        } else {
            throw new IllegalArgumentException("vector should be of same dimension");
        }
    }


    public static int findMinIndex(List<Double> arrayList) {
        int indexOfMin = 0;
        for (int i = 0; i < arrayList.size(); i++) {
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


