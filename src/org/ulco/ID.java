package org.ulco;

public class ID {
    private static ID instance = null;
    private int id = 0;

    private ID() {
    }

    public static ID instance() {
        if (null == instance) {
            instance = new ID();
        }

        return instance;
    }

    public int generate() {
        this.id++;

        return id;
    }
}