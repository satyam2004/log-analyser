package com.github.satyam2004.loganalyser.model;

import java.util.Arrays;

public enum State {
    STARTED("STARTED"),
    FINISHED("FINISHED");

    private final String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static State fromValue(String text) {
        return Arrays.stream(values())
                .filter(v -> v.getValue().equals(text))
                .findFirst()
                .orElse(null);
    }
}
