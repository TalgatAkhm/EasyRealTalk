package ru.mtl.VoidVoice.worker;

import ru.mtl.VoidVoice.model.ValuableObject;

public interface Presenter {
    void motionVectorHandler(ValuableObject valuableObject);
    void motionHandler(ValuableObject valuableObject);
}
