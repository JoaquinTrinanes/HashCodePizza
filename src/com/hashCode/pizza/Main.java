package com.hashCode.pizza;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader input = null;
        String file = "example.in";
        if(args.length != 0) file = args[0];
        try {
            input = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        Pizza p = new Pizza(input);
    }
}
