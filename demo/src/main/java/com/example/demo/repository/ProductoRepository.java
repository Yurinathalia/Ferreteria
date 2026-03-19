package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Producto;

public interface ProductoRepository {
    List<Producto> findAll();

    Optional<Producto> findById(int id);

    /**
     * Guarda el producto.
     * Si el producto tiene id = 0, se asigna un id nuevo.
     * Si ya existe, se actualiza.
     */
    Producto save(Producto producto);

    boolean deleteById(int id);
}
