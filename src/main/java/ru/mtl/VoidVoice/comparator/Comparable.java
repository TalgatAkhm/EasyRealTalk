package ru.mtl.VoidVoice.comparator;

/**
 * Interface for model classes, which could be compared.
 */
public interface Comparable<T> {
    double compareTo(T object);
}
