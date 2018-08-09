package ru.mtl.VoidVoice.comparator;

import ru.mtl.VoidVoice.model.KeyPoint;
import ru.mtl.VoidVoice.model.ValuableObject;

/**
 * Class which contains method to
 * compare two ValuableObjects (MotionVector or Motion).
 * **/
public final class Compare {

    /**
     * Light-weight compare of two {@link ValuableObject}. Checks
     * whether ValuableObjects are the same type and calls realisation
     * of {@link ValuableObject#compareWith(ValuableObject)}.
     *
     * @param valuableObject an object which we received from Leap Motion
     * @param keyPoint a key point from the tree
     * @return the result of the function {@link ValuableObject#compareWith(ValuableObject)}
     * or zero if ValuableObjects are different types.
     * **/
    public static double compareValuableObjects(KeyPoint valuableObject, KeyPoint keyPoint) {
        // If keypoints are the same type, then compare them
        if ((valuableObject.getBaseMotion() == null && keyPoint.getBaseMotion() == null) ||
                (valuableObject.getBaseVector() == null && keyPoint.getBaseVector() == null)) {
            return keyPoint.compareTo(valuableObject);
        }
        return 0;
    }
}
