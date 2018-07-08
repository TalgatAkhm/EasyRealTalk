package ru.mtl.VoidVoice.tree;

import com.leapmotion.leap.Vector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TouchChecker {
    private List<Vector> points;
    private final static float EPS = 0.01f;

    public TouchChecker(List<Vector> points) {
        this.points = points;
    }


    public List<List<Integer>> check() {
        List<List<Integer>> touches = new ArrayList<>(points.size());
        for (int i = 0; i < points.size(); ++i) {
            touches.set(i, new ArrayList<>(points.size()));
            for (int j = 0; j < points.size(); ++j) {
                if (distance(points.get(i), points.get(j)) < EPS) {
                    //touches[i][j] = 1;
                    touches.get(i).set(j, 1);
                }
            }
        }
        return touches;
    }

    private float distance(Vector v1, Vector v2) {
        return (float) Math.sqrt(to2(v1.getX() - v2.getX()) +
                to2(v1.getY() - v2.getY()) +
                to2(v1.getZ() - v2.getZ()));
    }

    private float to2(float num) {
        return num * num;
    }
}
