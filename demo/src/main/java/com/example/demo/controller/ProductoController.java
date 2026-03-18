package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // GET
    @GetMapping
    public List<Producto> obtener() {
        return service.obtenerProductos();
    }

    // POST
    @PostMapping
    public Producto agregar(@RequestBody Producto producto) {
        return service.agregarProducto(producto);
    }

    // PUT
    @PutMapping("/{id}")
    public Producto editar(@PathVariable int id, @RequestBody Producto producto) {
        return service.editarProducto(id, producto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return service.eliminarProducto(id);
    }
}