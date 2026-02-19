package com.seminario.mantenibilidad;

import java.util.List;

public class CarritoCompraBien {
    // CÓDIGO B: Alta Mantenibilidad (Refactorizado)
    public double calculateFinalPrice(List<Item> items, int customerType, boolean isVip) {
        double baseTotal = calculateBaseTotal(items);
        return baseTotal - calculateDiscount(baseTotal, customerType, isVip);
    }

    private double calculateBaseTotal(List<Item> items) {
        return items.stream().mapToDouble(Item::getP).sum();
    }

    private double calculateDiscount(double baseTotal, int customerType, boolean isVip) {
        if (customerType == 1) return baseTotal * (isVip ? 0.15 : 0.05);
        if (customerType == 2 && baseTotal > 100) return baseTotal * 0.10;
        return 0;
    }
}
