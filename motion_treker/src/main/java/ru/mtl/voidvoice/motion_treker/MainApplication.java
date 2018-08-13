package ru.mtl.voidvoice.motion_treker;

import ru.mtl.voidvoice.motion_treker.worker.RangeWorker;
import ru.mtl.voidvoice.motion_treker.worker.Worker;

/**
 * if first command line arguments equals "range"
 * means start up application in RangeMode
 * **/
public class MainApplication {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("range")) {
            if (args.length > 1) {
                new RangeWorker(args[1]).run();
            } else {
                new RangeWorker("/Users/mac/projects/SignLanguageLeapMotion/EasyRealTalk/motion_treker/src/main/resources/range_result").run();
            }
        } else {
            new Worker().run();
        }
    }

}
