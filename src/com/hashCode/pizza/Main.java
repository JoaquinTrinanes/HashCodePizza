package com.hashCode.pizza;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static Pizza pizza = null;

    static {
        BufferedReader input = null;
        String file = "example.in";
        try {
            input = new BufferedReader(new FileReader(file));
            pizza = new Pizza(input);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }


    }

    public static void main(String[] args){
	// write your code here

    }
}
