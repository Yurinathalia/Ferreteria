package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Producto;

@Service
public class ProductoService {
    
private List<Producto> productos = new ArrayList<>();
    private int ultimoId = 0;

    public ProductoService() {
        // Datos iniciales
        productos.add(new Producto(++ultimoId, "Martillo", 15000, 10));
        productos.add(new Producto(++ultimoId, "Tornillo", 5000, 20));
        productos.add(new Producto(++ultimoId, "Tuerca", 5000, 15));
        productos.add(new Producto(++ultimoId, "Destornillador", 20000, 5));
        productos.add(new Producto(++ultimoId, "Taladro", 100000, 50));
    }

    // Obtener todos
    public List<Producto> obtenerProductos() {
        return productos;
    }

    // Agregar
    public Producto agregarProducto(Producto producto) {
        producto = new Producto(++ultimoId,
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock());

        productos.add(producto);
        return producto;
    }

    // Editar
    public Producto editarProducto(int id, Producto nuevo) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                p.setNombre(nuevo.getNombre());
                p.setPrecio(nuevo.getPrecio());
                p.setStock(nuevo.getStock());
                return p;
            }
        }
        return null;
    }

    // Eliminar
    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

}
