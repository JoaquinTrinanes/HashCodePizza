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

        //c.setTrozo(this);
    }

    public Slice(Slice s){
        this.numTomates = s.getNumTomates();
        this.numChamps = s.getNumChamps();
        this.p1 = new Point (s.getP1().x, s.getP1().y);
        this.p2 = new Point (s.getP2().x, s.getP2().y);
        this.celdas = new ArrayList<>(s.getCeldas());
    }


/*
0: izquierda    Restamos 1 a C (y-1)
1: arriba   Restamos 1 a R (x-1)
2: derecha  Sumamos 1 a C (y+1)
3: abajo    Sumamos 1 a R (x+1)

Ademas de crecer comprobamos que el crecimiento sea "legal".
 */
    public Slice crecer(int direccion) {
        Slice trozo = new Slice(this);
        Point p1tmp, p2tmp;

        switch (direccion) {
            case 0: //izquierda
                p1tmp = new Point(p1.x, p1.y -1);
                if (p1tmp.y < 0) return null;
                if (comprobar(p1tmp, p2)){
                    //TODO())
                    trozo.setP1(p1tmp);
                    trozo.anadirCeldas(); //Para actualizar las celdas del Slice
                    return trozo;
                }
                break;
            case 1: //arriba
                p1tmp = new Point(p1.x -1, p1.y);
                if (p1tmp.x < 0) return null;
                if (comprobar(p1tmp, p2)){
                    //TODO())
                    trozo.setP1(p1tmp);
                    trozo.anadirCeldas(); //Para actualizar las celdas del Slice
                    return trozo;
                }
                break;

            case 2: //derecha
                p2tmp = new Point(p2.x, p2.y +1);
                if (p2tmp.y >= Main.pizza.C) return null;
                if (comprobar(p1, p2tmp)){
                    //TODO())
                    trozo.setP2(p2tmp);
                    trozo.anadirCeldas(); //Para actualizar las celdas del Slice
                    return trozo;
                }
                break;
            case 3: //abajo
                p2tmp = new Point(p2.x +1, p2.y);
                if (p2tmp.x >= Main.pizza.R) return null;
                if (comprobar(p1, p2tmp)){
                    //TODO())
                    trozo.setP2(p2tmp);
                    trozo.anadirCeldas(); //Para actualizar las celdas del Slice
                    return trozo;
                }
                break;

        }
        return null;
    }

    public boolean comprobar(Point p1, Point p2) {
        Rectangle rect = new Rectangle(this.p1.x, this.p1.y, this.p2.y - this.p1.y+1, this.p2.x - this.p1.x+1);
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
    //Este método se utiliza solo en el trozo elegido de la función recursiva
    public void anadirCeldasFinal(){
        //Iteramos por las celdas del trozo
        for (Celda cell : this.celdas) {
            cell.setTrozo(this);
        }
    }

    //Método a utilizar en cada llamada de la función recursiva
    public void anadirCeldas(){
        //Iteramos por las celdas del trozo
        for(int r = p1.x;r <= p2.x;r++){
            for(int c = p1.y;c <= p2.y;c++){
                Celda cell = Main.pizza.getCeldas()[r][c];
                //Comprobamos si la celda no pertenece al trozo
                if(!this.celdas.contains(cell)){
                    this.celdas.add(cell);
                    if (cell.getIngrediente() == Ingrediente.Tomato) this.numTomates++;
                    else this.numChamps++;
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

    @Override
    public String toString(){
        return p1.x+" "+p1.y+" "+p2.x+" "+p2.y;
    }

    public boolean esValido(){
        return numChamps >= Main.pizza.L && numTomates >= Main.pizza.L && this.celdas.size() <= Main.pizza.H;
    }

    //Funcion que se encarga de hacer backtracking recursivo con el trozo y almacena en Lista todos los
    //trozos
    public static void formarTrozo(Slice s, List<Slice> Lista){
        if(s.getCeldas().size() > Main.pizza.H){
            return;
        }
        if(s.esValido()){
            Lista.add(s);
            return;
        }
        Slice s0 = s.crecer(0);
        Slice s1 = s.crecer(1);
        Slice s2 = s.crecer(2);
        Slice s3 = s.crecer(3);

        if(s0 != null) formarTrozo(s0, Lista);
        if(s1 != null) formarTrozo(s1, Lista);
        if(s2 != null) formarTrozo(s2, Lista);
        if(s3 != null) formarTrozo(s3, Lista);

    }

    public static Slice obtenerMejorSlice(List<Slice> lista){
        if(lista.size() <= 0) return null;
        Slice mejorSlice = lista.get(0);
        for(Slice s : lista){
            if(s.getCeldas().size() < mejorSlice.getCeldas().size()) mejorSlice = s;
        }
        return mejorSlice;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public int getNumTomates() {
        return numTomates;
    }

    public void setNumTomates(int numTomates) {
        this.numTomates = numTomates;
    }

    public int getNumChamps() {
        return numChamps;
    }

    public void setNumChamps(int numChamps) {
        this.numChamps = numChamps;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = new Point(p1);
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = new Point(p2);
    }
}
