package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.demo.dto.CompraRequestDTO;
import com.example.demo.dto.CompraResponseDTO;
import com.example.demo.dto.ProductoRequestDTO;
import com.example.demo.dto.ProductoResponseDTO;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponseDTO> obtenerProductos() {
        return productoRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProductoResponseDTO agregarProducto(ProductoRequestDTO request) {
        Producto saved = productoRepository.save(new Producto(0, request.getNombre(), request.getPrecio(), request.getStock()));
        return toResponse(saved);
    }

    public ProductoResponseDTO editarProducto(int id, ProductoRequestDTO nuevo) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: id=" + id));

        existente.setNombre(nuevo.getNombre());
        existente.setPrecio(nuevo.getPrecio());
        existente.setStock(nuevo.getStock());

        return toResponse(productoRepository.save(existente));
    }

    public boolean eliminarProducto(int id) {
        boolean eliminado = productoRepository.deleteById(id);
        if (!eliminado) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: id=" + id);
        }
        return true;
    }

    /**
     * Compra: descuenta el inventario y si el stock queda en 0, el producto se elimina del catálogo.
     */
    public CompraResponseDTO comprarProducto(int id, CompraRequestDTO request) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: id=" + id));

        int cantidad = request.getCantidad();
        if (cantidad <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cantidad inválida");
        }

        int stockActual = existente.getStock();
        if (cantidad > stockActual) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Stock insuficiente. stockActual=" + stockActual + ", cantidad=" + cantidad);
        }

        int nuevoStock = stockActual - cantidad;
        existente.setStock(nuevoStock);

        boolean eliminadoDelInventario = false;
        if (nuevoStock <= 0) {
            productoRepository.deleteById(id);
            eliminadoDelInventario = true;
        } else {
            productoRepository.save(existente);
        }

        return new CompraResponseDTO(toResponse(existente), cantidad, eliminadoDelInventario);
    }

    private ProductoResponseDTO toResponse(Producto producto) {
        return new ProductoResponseDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

}
