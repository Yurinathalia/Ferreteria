package com.example.demo.dto;

public class CompraResponseDTO {
    private ProductoResponseDTO producto;
    private int cantidadComprada;
    private boolean eliminadoDelInventario;

    public CompraResponseDTO() {
        // Constructor requerido para serialización
    }

    public CompraResponseDTO(ProductoResponseDTO producto, int cantidadComprada, boolean eliminadoDelInventario) {
        this.producto = producto;
        this.cantidadComprada = cantidadComprada;
        this.eliminadoDelInventario = eliminadoDelInventario;
    }

    public ProductoResponseDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoResponseDTO producto) {
        this.producto = producto;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public boolean isEliminadoDelInventario() {
        return eliminadoDelInventario;
    }

    public void setEliminadoDelInventario(boolean eliminadoDelInventario) {
        this.eliminadoDelInventario = eliminadoDelInventario;
    }
}

