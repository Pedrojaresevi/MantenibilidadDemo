package com.seminario.mantenibilidad;

import java.util.List;

public class CarritoCompra {

    // CÓDIGO A: Muy baja mantenibilidad
    public double calc(List<ItemCarrito> l, int cType, boolean isVip, boolean hasCoupon, String season) {
        double t = 0;
        // Sumar precios solo si existen y >0
        for (int i = 0; i < l.size(); i++) {
            ItemCarrito it = l.get(i);
            if (it != null) {
                if (it.getP() > 0) {
                    t += it.getP();
                }
            }
        }

        // Aplicar descuentos según tipo cliente y VIP
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

        // Código duplicado de verificación
        if (cType == 1) {
            if (t > 50) {
                System.out.println("Cliente tipo 1, compra > 50");
            }
        } else if (cType == 2) {
            if (t > 50) {
                System.out.println("Cliente tipo 2, compra > 50");
            }
        }

        // Mezclar impresión de carrito
        System.out.println("Items en carrito:");
        for (int i = 0; i < l.size(); i++) {
            ItemCarrito it = l.get(i);
            if (it != null) {
                System.out.println("Precio item " + i + ": " + it.getP());
            }
        }

        // Más lógica innecesaria
        if (season.equals("WINTER")) {
            t = t + 5; // recargo invierno
        } else if (season.equals("SPRING")) {
            t = t + 2; // recargo primavera
        } else if (season.equals("SUMMER")) {
            t = t - 3; // descuento verano
        } else {
            t = t; // nada
        }

        // Lógica confusa y duplicada
        double t2 = t;
        if (t2 > 100) {
            t2 = t2 - 10;
        } else if (t2 > 50) {
            t2 = t2 - 5;
        } else {
            t2 = t2;
        }

        if (t > 100) {
            t = t - 10;
        } else if (t > 50) {
            t = t - 5;
        }

        // Función gigante, mezcla de validaciones
        for (int i = 0; i < l.size(); i++) {
            ItemCarrito it = l.get(i);
            if (it != null) {
                if (it.getP() > 0) {
                    if (it.getP() > 50) {
                        System.out.println("Item caro: " + it.getP());
                        t = t + 2; // recargo especial
                    } else {
                        t = t + 1; // recargo pequeño
                    }
                } else {
                    System.out.println("Item sin precio");
                }
            }
        }

        // Código comentado inútil
        // double x = t + t2;

        return t;
    }

    // Método duplicado inútil
    public void imprimirItems(List<ItemCarrito> l) {
        System.out.println("=== Imprimiendo Items ===");
        for (int i = 0; i < l.size(); i++) {
            ItemCarrito it = l.get(i);
            if (it != null) {
                System.out.println("Item " + i + ": " + it.getP());
            }
        }
    }

    // Más métodos innecesarios
    public void dummyMethod1() {
        int a = 5;
        int b = 10;
        int c = a + b;
        System.out.println("Dummy 1: " + c);
    }

    public void dummyMethod2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Dummy 2 loop " + i);
        }
    }

}

// Clases de apoyo para que compile
class ItemCarrito {
    private double p;

    public ItemCarrito(double p) { this.p = p; }

    public double getP() { return p; }
}
