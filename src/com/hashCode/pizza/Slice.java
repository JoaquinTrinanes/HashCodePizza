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

        c.setTrozo(this);
    }
/*
0: izquierda
1: arriba
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
                if (comprobar(p1tmp, p2)){
                    //TODO())
                    this.p1 = p1tmp;
                    anadirCeldas(); //Para actualizar las celdas del Slice
                    return true;
                }
                break;
            case 1: //arriba
                p1tmp = new Point(p1.x, p1.y - 1);
                if (p1tmp.y < 0) return false;
                if (comprobar(p1tmp, p2)){
                    //TODO())
                    this.p1 = p1tmp;
                    anadirCeldas(); //Para actualizar las celdas del Slice
                    return true;
                }
                break;

            case 2: //derecha
                p2tmp = new Point(p2.x + 1, p2.y);
                if (p2tmp.x >= Main.pizza.R) return false;
                if (comprobar(p1, p2tmp)){
                    //TODO())
                    this.p2 = p2tmp;
                    anadirCeldas(); //Para actualizar las celdas del Slice
                    return true;
                }
                break;
            case 3: //abajo
                p2tmp = new Point(p2.x, p2.y +1);
                if (p2tmp.y >= Main.pizza.C) return false;
                if (comprobar(p1, p2tmp)){
                    //TODO())
                    this.p2 = p2tmp;
                    anadirCeldas(); //Para actualizar las celdas del Slice
                    return true;
                }
                break;
            default:
                return false;

        }
        return false;
    }

    public boolean comprobar(Point p1, Point p2) {
        Rectangle rect = new Rectangle(this.p1.x, this.p1.y, p2.x - p1.x, p2.y - p1.y);
        boolean valido = true;
        for (int r = p1.x; r <= p2.x; r++) {
            for (int c = p1.y; c <= p2.y; c++) {
                /*
                Comprobamos si la celda iterada es nueva en el slice
                 */
                if (!rect.contains(new Point(r, c))) {
                    //zona nueva
                    Celda cell = Main.pizza.getCeldas()[r][c];
                    if (!(cell.getTrozo() == null || cell.getTrozo() == this)) {
                        //el trozo no es valido
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Se encarga de añadir todas las celdas contenidas entre los puntos p1 y p2 en caso de que no esten
    //También fija el atributo trozo de cada celda a este trozo (this)
    public void anadirCeldas(){
        //Iteramos por las celdas del trozo
        for(int r = p1.x;r <= p2.x;r++){
            for(int c = p1.y;c <= p2.y;c++){
                Celda cell = Main.pizza.getCeldas()[r][c];
                //Comprobamos si la celda no pertenece al trozo
                if(cell.getTrozo() == null){
                    cell.setTrozo(this);
                    this.celdas.add(cell);
                }
            }
        }
    }

    //Se encarga de borrar el atributo slice de las celdas contenidas
    public void borrarSlice(){

        for (Celda cell : this.celdas) {
            cell.setTrozo(null);
        }
        this.celdas.clear();

    }

}
