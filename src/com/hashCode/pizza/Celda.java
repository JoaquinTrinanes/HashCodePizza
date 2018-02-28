package com.hashCode.pizza;

import java.awt.Point;

public class Celda {
    private Slice trozo = null;
    private Point punto = null;
    private Ingrediente ingrediente = null;

    public Celda(Point punto, Ingrediente ing) {
        this.punto = new Point(punto);
        this.ingrediente = ing;
    }

    public Slice getTrozo() {
        return trozo;
    }

    public void setTrozo(Slice trozo) {
        this.trozo = trozo;
    }

    public Point getPunto() {
        return punto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    @Override
    public String toString() {
        return "("+punto.x+","+punto.y+")";
    }


    @Override
    public boolean equals(Object c) {
        Celda cell = (Celda) c;
        return this.toString().equals(cell.toString()) && cell instanceof Celda;
    }

}
