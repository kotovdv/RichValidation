package com.jquartz.rich.validation.core.verification.builder.must;

import com.jquartz.rich.validation.core.verification.builder.MustPartBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartsJoinedByAndBuilder<T> {

    private List<MustPartBuilder<T>> mustParts = new ArrayList<>();

    public void addExpression(MustPartBuilder<T> mustPartBuilder) {
        this.mustParts.add(mustPartBuilder);
    }

    public List<MustPartBuilder<T>> getMustParts() {
        return Collections.unmodifiableList(mustParts);
    }
}
