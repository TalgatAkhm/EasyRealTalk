package ru.mtl.VoidVoice.worker;

import ru.mtl.VoidVoice.model.Motion;
import ru.mtl.VoidVoice.model.MotionVector;

public interface Presenter {
    void motionVectorHandler(MotionVector motionVector);
    void motionVectorHandler(Motion motion);
}
