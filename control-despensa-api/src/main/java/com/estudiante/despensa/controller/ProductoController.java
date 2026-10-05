package com.estudiante.despensa.controller;

import com.estudiante.despensa.model.Producto;
import com.estudiante.despensa.model.ResumenInventario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {

        registrarProducto(
                new Producto(1L, "Leche", "Lacteos",
                        2, 12.50));

        registrarProducto(
                new Producto(2L, "Yogurt", "Lacteos",
                        3, 6.00));

        registrarProducto(
                new Producto(3L, "Arroz", "Granos",
                        4, 8.50));

        registrarProducto(
                new Producto(4L, "Frijol", "Granos",
                        5, 10.00));

        registrarProducto(
                new Producto(5L, "Detergente", "Limpieza",
                        2, 28.00));

        registrarProducto(
                new Producto(6L, "Jugo", "Bebidas",
                        9, 7.50));
    }

    private void registrarProducto(Producto producto) {

        for (Producto existente : productos) {

            if (existente.getId().equals(producto.getId())) {
                throw new IllegalArgumentException(
                        "No se permiten identificadores duplicados");
            }
        }

        productos.add(producto);
    }

    // 1. Consultar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos() {

        return ResponseEntity.ok(productos);
    }

    // 2. Buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(
            @PathVariable Long id) {

        for (Producto producto : productos) {

            if (producto.getId().equals(id)) {
                return ResponseEntity.ok(producto);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // 3. Buscar productos por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(
            @PathVariable String categoria) {

        List<Producto> encontrados = new ArrayList<>();

        for (Producto producto : productos) {

            if (producto.getCategoria()
                    .equalsIgnoreCase(categoria)) {

                encontrados.add(producto);
            }
        }

        return ResponseEntity.ok(encontrados);
    }

    // 4. Productos con stock bajo
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> obtenerStockBajo() {

        List<Producto> stockBajo = new ArrayList<>();

        for (Producto producto : productos) {

            if (producto.getCantidad() <= 3) {
                stockBajo.add(producto);
            }
        }

        return ResponseEntity.ok(stockBajo);
    }

    // 5. Producto de mayor valor
    @GetMapping("/mayor-valor")
    public ResponseEntity<Producto> obtenerMayorValor() {

        Producto mayor = productos.get(0);

        for (Producto producto : productos) {

            if (producto.calcularSubtotal()
                    > mayor.calcularSubtotal()) {

                mayor = producto;
            }
        }

        return ResponseEntity.ok(mayor);
    }

    // 6. Resumen del inventario
    @GetMapping("/resumen")
    public ResponseEntity<ResumenInventario> obtenerResumen() {

        int cantidadProductos = productos.size();
        int totalUnidades = 0;
        double valorTotal = 0;

        for (Producto producto : productos) {

            totalUnidades += producto.getCantidad();

            valorTotal += producto.calcularSubtotal();
        }

        ResumenInventario resumen =
                new ResumenInventario(
                        cantidadProductos,
                        totalUnidades,
                        valorTotal);

        return ResponseEntity.ok(resumen);
    }
}