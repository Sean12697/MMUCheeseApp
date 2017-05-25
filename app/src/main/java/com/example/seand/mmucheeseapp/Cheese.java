package com.example.seand.mmucheeseapp;

import java.io.Serializable;

public class Cheese implements Serializable {
    String name;
    String description;

    Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
