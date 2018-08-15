package ru.mtl.VoidVoice.comparator;

public interface ObjectComparator<T> {
    double compare(int layerIndex, T object);
}
