package com.hashCode.pizza;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.io.*;

public class Pizza {

    private final Ingrediente[][] pizza;
    private final int rows, columns, minIngredientes, maxCeldas;
    private int numTomate = 0, numChamp = 0;
    private final Ingrediente menorIng;

    public Pizza(BufferedReader input) throws IOException {
        String[] data = input.readLine().split("\\s");
        this.rows = Integer.parseInt(data[0]);
        this.columns = Integer.parseInt(data[1]);
        this.minIngredientes = Integer.parseInt(data[2]);
        this.maxCeldas = Integer.parseInt(data[3]);

        this.pizza = new Ingrediente[rows][columns];

        for (int i = 0; i < rows; i++) {
            char[] ing = new char[columns];
            input.readLine().getChars(0, columns, ing, 0);
            for (int j = 0; j < ing.length; j++) {
                pizza[i][j] = Ingrediente.valor(ing[j]);
                if (pizza[i][j] == Ingrediente.Tomato) numTomate++;
                else numChamp++;
            }
        }
        if (this.numTomate < this.numChamp) this.menorIng = Ingrediente.Tomato;
        else this.menorIng = Ingrediente.Mushroom;

        System.out.println("Pizza creada:");
        for (Ingrediente[] fila : pizza) {
            for (Ingrediente ing : fila) {
                System.out.print(ing);
            }
            System.out.println();
        }
        System.out.println("Champiñones: " + numChamp);
        System.out.println("Tomates: " + numTomate);
    }

    public Rectangle toRectangle() {
        return new Rectangle(0, 0, this.columns, this.rows);
    }


}