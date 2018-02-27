package com.hashCode.pizza;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Slice {

    private int numTomates = 0;
    private int numChamps = 0;
    private Point p1 = null, p2 = null;

    private List<Celda> celdas = null;


    public Slice(Celda c) {
        this.celdas = new ArrayList<>();
        celdas.add(c);
        if (c.getIngrediente() == Ingrediente.Tomato) this.numTomates++;
        else this.numChamps++;

        this.p1 = new Point(c.getPunto());
        this.p2 = new Point(c.getPunto());
    }
/*
0:  izquierda
1:  arriba
2: derecha
3: abajo

Ademas de crecer comprobamos que el crecimiento sea "legal".
 */
    public boolean crecer(int direccion) {
        Point p1tmp, p2tmp;

        switch (direccion) {
            case 0: //izquierda
                p1tmp = new Point(p1.x - 1, p1.y);
                if (p1tmp.x < 0) return false;
                if (comprobar(p1tmp, p2)) return true; //TODO())
                break;
            case 1: //arriba

                break;

            case 2: //derecha

                break;
            case 3: //abajo

                break;
            default:
                return false;

        }
    }

    public boolean comprobar(Point p1, Point p2) {
        Rectangle r = new Rectangle(this.p1.x, this.p1.x, p2.y - p1.y, p2.x - p1.x);
        boolean valido = true;
        for (int i = p1.x; i <= p2.x; i++) {
            for (int j = p1.y; j <= p2.y; j++) {
                /*
                Comprobamos si la celda iterada es nueva en el slice
                 */
                if (!r.contains(new Point(i, j))) {
                    //zona nueva
                    Celda c = Main.pizza.getPizza()[i][j];
                    if (!(c.getTrozo() == null || c.getTrozo() == this)) {
                        //el trozo no es valido
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
