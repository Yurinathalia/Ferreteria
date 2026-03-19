package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

import com.example.demo.dto.CompraRequestDTO;
import com.example.demo.dto.CompraResponseDTO;
import com.example.demo.dto.ProductoRequestDTO;
import com.example.demo.dto.ProductoResponseDTO;
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
    public List<ProductoResponseDTO> obtener() {
        return service.obtenerProductos();
    }

    // POST
    @PostMapping
    public ProductoResponseDTO agregar(@Valid @RequestBody ProductoRequestDTO producto) {
        return service.agregarProducto(producto);
    }

    // PUT
    @PutMapping("/{id}")
    public ProductoResponseDTO editar(@PathVariable int id, @Valid @RequestBody ProductoRequestDTO producto) {
        return service.editarProducto(id, producto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return service.eliminarProducto(id);
    }

    // POST Compra
    @PostMapping("/{id}/comprar")
    public CompraResponseDTO comprar(@PathVariable int id, @Valid @RequestBody CompraRequestDTO request) {
        return service.comprarProducto(id, request);
    }
}