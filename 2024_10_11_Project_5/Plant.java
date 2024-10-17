// package com.gradescope.garden;
public class Plant {
    protected String name;
    protected int growth;

    public Plant(String name) {
        this.name = name;
    }

    public int getGrowth() {
        return this.growth;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
