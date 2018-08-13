package ru.mtl.VoidVoice.model;

import ru.mtl.VoidVoice.comparator.Comparable;

public interface ValuableObject extends Comparable<ValuableObject> {
    boolean isMotion();
}
