package com.seminario.mantenibilidad;

import java.util.List;
import java.util.Objects;

public class CarritoCompraBien {

    /**
     * Calcula el total del carrito aplicando descuentos según tipo de cliente,
     * VIP, cupones y temporada.
     */
    public double calcularTotal(List<ItemCarritoBien> items, TipoCliente tipo, boolean esVip, boolean tieneCupon, Temporada temporada) {
        double total = sumarPrecios(items);
        total = aplicarDescuentoCliente(total, tipo, esVip, tieneCupon);
        total = aplicarDescuentoTemporada(total, tipo, temporada);
        total = aplicarRecargosEspeciales(items, total);
        return total;
    }

    /**
     * Suma los precios de todos los items válidos (precio > 0)
     */
    private double sumarPrecios(List<ItemCarritoBien> items) {
        return items.stream()
                .filter(item -> item != null && item.getPrecio() > 0)
                .mapToDouble(ItemCarritoBien::getPrecio)
                .sum();
    }

    /**
     * Aplica descuentos según tipo de cliente y si es VIP o tiene cupón
     */
    private double aplicarDescuentoCliente(double total, TipoCliente tipo, boolean esVip, boolean tieneCupon) {
        switch (tipo) {
            case REGULAR:
                return total * 0.95; // 5% descuento regular
            case VIP:
                if (esVip) {
                    if (tieneCupon) {
                        return total * 0.80; // 20% descuento
                    } else {
                        return total * 0.85; // 15% descuento
                    }
                } else {
                    return total * 0.95; // 5% descuento si no es VIP
                }
            default:
                return total;
        }
    }

    /**
     * Aplica descuentos según la temporada y tipo de cliente
     */
    private double aplicarDescuentoTemporada(double total, TipoCliente tipo, Temporada temporada) {
        if (tipo == TipoCliente.VIP && total > 100) {
            if (Objects.requireNonNull(temporada) == Temporada.VERANO) {
                return total * 0.85; // 15% descuento verano
            }
            return total * 0.90; // 10% descuento otras temporadas
        }
        return total;
    }

    /**
     * Aplica recargos o ajustes especiales según temporada o precio de los items
     */
    private double aplicarRecargosEspeciales(List<ItemCarritoBien> items, double total) {
        // Ajuste por temporada
        for (Temporada temp : Temporada.values()) {
            if (temp == Temporada.INVIERNO) {
                total += 5;
            } else if (temp == Temporada.PRIMAVERA) {
                total += 2;
            } else if (temp == Temporada.VERANO) {
                total -= 3;
            }
        }

        // Recargos especiales por precio individual
        for (ItemCarritoBien item : items) {
            if (item != null && item.getPrecio() > 0) {
                total += item.getPrecio() > 50 ? 2 : 1;
            }
        }
        return total;
    }

    /**
     * Imprime los items de manera ordenada
     */
    public void imprimirItems(List<ItemCarritoBien> items) {
        System.out.println("=== Items en carrito ===");
        for (int i = 0; i < items.size(); i++) {
            ItemCarritoBien item = items.get(i);
            if (item != null) {
                System.out.println("Item " + i + ": " + item.getPrecio());
            }
        }
    }

    // Enumeraciones para claridad
    public enum TipoCliente { REGULAR, VIP }
    public enum Temporada { VERANO, INVIERNO, PRIMAVERA, OTONO }

}

// Clase Item refactorizada
class ItemCarritoBien {
    private double precio;

    public ItemCarritoBien(double precio) { this.precio = precio; }
    public double getPrecio() { return precio; }
}
