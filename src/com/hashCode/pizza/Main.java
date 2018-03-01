package com.hashCode.pizza;

import java.io.*;
import java.util.ArrayList;

public class Main {

    static Pizza pizza = null;

    static String nombre, file, out;

    static {
        BufferedReader input = null;
        Main.nombre = "big";
        Main.file = nombre + ".in";
        Main.out = nombre + ".out";
        try {
            input = new BufferedReader(new FileReader(file));
            pizza = new Pizza(input);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }


    }

    public static void main(String[] args) throws IOException {
	//Itera por toda la pizza buscando una celda con el ingrediente de menor numero
        for(int r = 0;r < pizza.R;r++){
            for(int c = 0;c < pizza.C;c++){
                Celda cell = pizza.getCeldas()[r][c];
                if(cell.getTrozo() == null && cell.getIngrediente() == pizza.MENOR_ING){
                    Slice s = new Slice(cell);
                    ArrayList<Slice> lista = new ArrayList<>();
                    Slice.formarTrozo(s, lista);
                    Slice.seguir = true;
                    if(lista.size() > 0) {
                        Slice mejorSlice = Slice.obtenerMejorSlice(lista);
                        mejorSlice.anadirCeldasFinal();
                        Main.pizza.anadirTrozo(mejorSlice);
                        if (Main.pizza.getTrozos().size() % 100 == 0) {
                            System.out.println("Formados " + Main.pizza.getTrozos().size() + " trozos");
                        }
                    }
                }
            }
        }
        System.out.println("Calculado. Escribiendo a archivo...");
        PrintWriter writer = new PrintWriter(Main.out, "ASCII");
        System.out.println(Main.pizza.getTrozos().size());
        writer.println(Main.pizza.getTrozos().size());
        for(Slice s : Main.pizza.getTrozos()){
            System.out.println(s);
            writer.println(s);
        }
        writer.close();
    }
}
