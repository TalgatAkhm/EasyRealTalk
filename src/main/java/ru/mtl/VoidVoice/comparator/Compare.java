package ru.mtl.VoidVoice.comparator;

import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.model.ValuableObject;

/**
 * Class which contains method to
 * compare two ValuableObjects (MotionVector or Motion).
 * **/
public final class Compare {

    /**
     * Light-weight compare of two {@link Comparable}. Checks
     * whether ValuableObjects are the same type and calls realisation
     * of {@link Comparable#compareTo(Object)}.
     *
     * @param valuableObject an object which we received from Leap Motion
     * @param keyPoint a key point from the tree
     * @return the result of the function {@link Comparable#compareTo(Object)}
     * or zero if ValuableObjects are different types.
     * **/
    public static double compareValuableObjects(Comparable valuableObject, Comparable keyPoint) {
        return 0;
    }
}
