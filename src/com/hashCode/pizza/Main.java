package com.hashCode.pizza;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static Pizza pizza = null;

    static {
        BufferedReader input = null;
        String file = "small.in";
        try {
            input = new BufferedReader(new FileReader(file));
            pizza = new Pizza(input);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }


    }

    public static void main(String[] args){
	//Itera por toda la pizza buscando una celda con el ingrediente de menor numero
        for(int r = 0;r < pizza.R;r++){
            for(int c = 0;c < pizza.C;c++){
                Celda cell = pizza.getCeldas()[r][c];
                if(cell.getTrozo() == null && cell.getIngrediente() == pizza.MENOR_ING){
                    Slice s = new Slice(cell);
                    ArrayList<Slice> lista = new ArrayList<>();
                    Slice.formarTrozo(s, lista);
                    if(lista.size() > 0) {
                        Slice mejorSlice = Slice.obtenerMejorSlice(lista);
                        mejorSlice.anadirCeldasFinal();
                        Main.pizza.anadirTrozo(mejorSlice);
                    } else {
                        cell.setTrozo(null);
                    }
                }
            }
        }
        for(Slice s : Main.pizza.getTrozos()){
            System.out.println(s);
        }
    }
}
