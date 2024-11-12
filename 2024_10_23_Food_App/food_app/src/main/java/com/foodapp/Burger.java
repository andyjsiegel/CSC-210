package com.foodapp;

public class Burger extends Food {

    // Constructor
    public Burger() {
        this.label = "CHEESEBURGER";
    }

    // Implement getPrice for Burger
    @Override
    public double getPrice() {
        return 5.99;  // Simple flat price for a burger
    }
}
