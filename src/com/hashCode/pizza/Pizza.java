package com.hashCode.pizza;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private final Celda[][] celdas;
    public static int R = 0, C = 0, L = 0, H = 0;
    private int numTomate = 0, numChamp = 0;
    public static Ingrediente MENOR_ING;
    private List<Slice> trozos;

    public Celda[][] getCeldas() {
        return celdas;
    }

    public Pizza(BufferedReader input) throws IOException {
        String[] data = input.readLine().split("\\s");
        this.R = Integer.parseInt(data[0]);
        this.C = Integer.parseInt(data[1]);
        this.L = Integer.parseInt(data[2]);
        this.H = Integer.parseInt(data[3]);

        trozos = new ArrayList<>();

        this.celdas = new Celda[R][C];

        for (int r = 0; r < R; r++) {
            char[] ing = new char[C];
            input.readLine().getChars(0, C, ing, 0);
            for (int c = 0; c < ing.length; c++) {
                celdas[r][c] =  new Celda(new Point(r, c), Ingrediente.valor(ing[c])); //  Ingrediente.valor(ing[j]);
                if (celdas[r][c].getIngrediente() == Ingrediente.Tomato) numTomate++;
                else numChamp++;
            }
        }
        if (this.numTomate < this.numChamp) this.MENOR_ING = Ingrediente.Tomato;
        else this.MENOR_ING = Ingrediente.Mushroom;

        System.out.println("Pizza creada:");
        for (Celda[] fila : celdas) {
            for (Celda cell : fila) {
                System.out.print(cell.getIngrediente());
            }
            System.out.println();
        }
        System.out.println("Champiñones: " + numChamp);
        System.out.println("Tomates: " + numTomate);
    }

    public List<Slice> cortar() {
        return null;
    }

    public List<Slice> getTrozos() {
        return trozos;
    }

    public void anadirTrozo(Slice s){
        this.trozos.add(s);
    }
}
