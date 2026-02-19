package com.seminario.mantenibilidad;

import java.util.List;

public class CarritoCompra {

    // CÓDIGO A: Baja Mantenibilidad
    public double calc(List<Item> l, int cType, boolean isVip, boolean hasCoupon, String season) {
        double t = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) != null) {
                if (l.get(i).getP() > 0) {
                    t += l.get(i).getP();
                }
            }
        }
        if (cType == 1) {
            if (isVip) {
                if (hasCoupon) {
                    t = t - (t * 0.20);
                } else {
                    t = t - (t * 0.15);
                }
            } else {
                t = t - (t * 0.05);
            }
        } else if (cType == 2) {
            if (t > 100) {
                if (season.equals("SUMMER")) {
                    t = t - (t * 0.15);
                } else {
                    t = t - (t * 0.10);
                }
            }
        }
        return t;
    }
//Hola
}
// Clases de apoyo para que el código compile
class Item {
    private double p;
    public double getP() { return p; }
}