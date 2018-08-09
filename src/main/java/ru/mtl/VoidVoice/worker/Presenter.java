package ru.mtl.VoidVoice.worker;

import ru.mtl.VoidVoice.model.MotionVector;
import ru.mtl.VoidVoice.model.ValuableObject;

public interface Presenter {
    void motionVectorHandler(MotionVector valuableObject);
    void motionHandler(ValuableObject valuableObject);
}
