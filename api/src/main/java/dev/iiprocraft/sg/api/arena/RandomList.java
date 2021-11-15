package dev.iiprocraft.sg.api.arena;

import java.util.*;

public class RandomList<E> extends ArrayList<E> {

    private final NavigableMap<Double, E> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public RandomList() {
        this(new Random());
    }

    public RandomList(Random random) {
        this.random = random;
    }

    public RandomList<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    @Override
    public boolean add(E e) {
        this.add(1.0, e);
        return super.add(e);
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

}