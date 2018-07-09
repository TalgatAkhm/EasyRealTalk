package ru.mtl.VoidVoice.comparator;

import ru.mtl.VoidVoice.model.ValuableObject;

public final class Compare {
    public static double compareValuableObjects(ValuableObject valuableObject, ValuableObject keyPoint) {
        if ((valuableObject.isMotion() && keyPoint.isMotion()) ||
                (!valuableObject.isMotion() && !keyPoint.isMotion())) {
            return keyPoint.compareWith(valuableObject);
        }
        return 0;
    }
}
