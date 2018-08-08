package ru.mtl.voidvoice.motion_treker;

import ru.mtl.voidvoice.motion_treker.worker.Worker;

public class MainApplication {

    public static void main(String[] args) {
        new Worker().run();
    }

}
