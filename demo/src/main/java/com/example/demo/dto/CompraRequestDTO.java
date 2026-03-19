package com.example.demo.dto;

import jakarta.validation.constraints.Min;

public class CompraRequestDTO {
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

