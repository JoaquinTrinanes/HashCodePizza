package com.hashCode.pizza;


import java.awt.Rectangle;

public class Slice {
    private Rectangle forma;
    /*
    int numTomates;
    int numChamps;
     */

    public Slice() {
        //x,y son el punto superior izquierdo
        /*
        Creo que puede ser interesante usar esta clase porque tiene metodos como contains y demas para ver si se
        intercalan los trozos que tenemos, pero a ver
         */
        forma = new Rectangle(0, 0, 10,10);

    }
}
