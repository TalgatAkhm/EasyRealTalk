package ru.mtl.VoidVoice.worker;

import com.leapmotion.leap.Frame;
import ru.mtl.VoidVoice.model.MotionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotionTypeDetector {
    private final static double MIN_NECESSARY_VALUE = 0.4;
    private List<Frame> localFrames;
    private Map<MotionType, Double> probabilities;
    private Presenter presenter;

    MotionTypeDetector(Presenter presenter) {
        this.presenter = presenter;

        localFrames = new ArrayList<>();
        probabilities = new HashMap<>();

        probabilities.put(MotionType.LeftSwipe, 0.0);
        probabilities.put(MotionType.RightSwipe, 0.0);
        probabilities.put(MotionType.UpSwipe, 0.0);
        probabilities.put(MotionType.DownSwipe, 0.0);
        probabilities.put(MotionType.Shake, 0.0);
    }

    public void tryToDetect(Frame frame) {
        if (localFrames.size() >= 10) {
            localFrames.clear();
        }

        localFrames.add(frame);

        tryLeftSwipeDetect();
        tryRightSwipeDetect();
        tryUpSwipeDetect();
        tryDownSwipeDetect();
        tryShakeDetect();
    }

    private void tryLeftSwipeDetect() {
        probabilities.replace(MotionType.LeftSwipe, 0.0);
    }

    private void tryRightSwipeDetect() {
        probabilities.replace(MotionType.RightSwipe, 0.0);
    }

    private void tryUpSwipeDetect() {
        probabilities.replace(MotionType.UpSwipe, 0.0);
    }

    private void tryDownSwipeDetect() {
        probabilities.replace(MotionType.DownSwipe, 0.0);
    }

    private void tryShakeDetect() {
        probabilities.replace(MotionType.Shake, 0.0);
    }
}
