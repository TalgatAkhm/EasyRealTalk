package ru.mtl.VoidVoice.utils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class MotionVectorTouchesConverter implements AttributeConverter<List<List<Integer>>, String> {

    private static final int MATRIX_SIZE = 10;

    @Override
    public String convertToDatabaseColumn(List<List<Integer>> matrix) {
        StringBuilder matrixToString = new StringBuilder();
        for (List<Integer> aMatrix : matrix) {
            for (Integer anAMatrix : aMatrix) {
                matrixToString.append(anAMatrix.toString());
            }
        }
        return matrixToString.toString();
    }

    @Override
    public List<List<Integer>> convertToEntityAttribute(String s) {
        List<List<Integer>> matrix = new ArrayList<>(10);
        for (int i = 0; i < MATRIX_SIZE; ++i) {
            matrix.add(new ArrayList<>(10));
            for (int j = 0; j < MATRIX_SIZE; ++j) {
                matrix.get(i).add(Integer.parseInt(s.charAt(i * MATRIX_SIZE + j) + ""));
            }
        }
        return matrix;
    }
}
