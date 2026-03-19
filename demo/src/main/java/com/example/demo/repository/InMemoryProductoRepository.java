package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Producto;

@Repository
public class InMemoryProductoRepository implements ProductoRepository {

    private final ConcurrentMap<Integer, Producto> productosPorId = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    public InMemoryProductoRepository() {
        // Catálogo inicial (10 productos).
        crearProducto("Martillo", 15000, 10);
        crearProducto("Tornillo", 5000, 20);
        crearProducto("Tuerca", 5000, 15);
        crearProducto("Destornillador", 20000, 5);
        crearProducto("Taladro", 100000, 50);
        crearProducto("Alicates", 18000, 12);
        crearProducto("Clavos", 6000, 30);
        crearProducto("Cinta Teflón", 2500, 60);
        crearProducto("Llave Inglesa", 22000, 8);
        crearProducto("Nivel", 14000, 6);
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>(productosPorId.values());
        productos.sort(Comparator.comparingInt(Producto::getId));
        return productos;
    }

    @Override
    public Optional<Producto> findById(int id) {
        return Optional.ofNullable(productosPorId.get(id));
    }

    @Override
    public Producto save(Producto producto) {
        if (producto.getId() == 0) {
            int id = nextId.incrementAndGet();
            Producto nuevo = new Producto(id, producto.getNombre(), producto.getPrecio(), producto.getStock());
            productosPorId.put(id, nuevo);
            return nuevo;
        }

        Producto actualizado = new Producto(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
        productosPorId.put(producto.getId(), actualizado);
        return actualizado;
    }

    @Override
    public boolean deleteById(int id) {
        return productosPorId.remove(id) != null;
    }

    private Producto crearProducto(String nombre, double precio, int stock) {
        int id = nextId.incrementAndGet();
        Producto producto = new Producto(id, nombre, precio, stock);
        productosPorId.put(id, producto);
        return producto;
    }
}

