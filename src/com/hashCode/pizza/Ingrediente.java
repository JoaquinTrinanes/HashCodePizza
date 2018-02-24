package com.hashCode.pizza;

public enum Ingrediente {
    Mushroom,
    Tomato;

    public static Ingrediente valor(char c) {
        switch (c) {
            case 'M':
                return Ingrediente.Mushroom;
            case 'T':
                return Ingrediente.Tomato;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        if (this == Ingrediente.Tomato) return "\033[31;1mT\033[0m";
        else if (this == Ingrediente.Mushroom) return "M";
        else return null;
    }
}
