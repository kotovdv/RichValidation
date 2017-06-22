package com.jquartz.rich.validation.core.util;

import java.util.HashSet;
import java.util.Set;

public class UnorderedPair<T> {

    private Set<T> set = new HashSet<>();

    public UnorderedPair(T left, T right) {
        this.set.add(left);
        this.set.add(right);
    }

    public static <T> UnorderedPair<T> unorderedPairOf(T left, T right) {
        return new UnorderedPair<>(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnorderedPair)) return false;

        UnorderedPair<?> that = (UnorderedPair<?>) o;

        return set != null ? set.equals(that.set) : that.set == null;
    }

    @Override
    public int hashCode() {
        return set != null ? set.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UnorderedPair{" +
                "set=" + set +
                '}';
    }
}