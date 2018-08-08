package ru.mtl.VoidVoice.comparator;

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
    public static double compareValuableObjects(ValuableObject valuableObject, ValuableObject keyPoint) {
        if ((valuableObject.isMotion() && keyPoint.isMotion()) ||
                (!valuableObject.isMotion() && !keyPoint.isMotion())) {
            return keyPoint.compareWith(valuableObject);
        }
        return 0;
    }
}
