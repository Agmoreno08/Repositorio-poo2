package org.agma.example_app.example1_app.controlers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class controloperator {

    private final List<Map<String, Object>> productos = new ArrayList<>();

    // Agregar producto a la factura
    @PostMapping("/agregarProducto")
    public Map<String, Object> agregarProducto(@RequestBody Map<String, Object> producto) {
        productos.add(producto);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto agregado exitosamente.");
        respuesta.put("producto", producto);

        return respuesta;
    }

    // Generar el total de la factura
    @PostMapping("/generarFactura")
    public Map<String, Object> generarFactura() {
        double total = 0;

        for (Map<String, Object> producto : productos) {
            double precio = (double) producto.get("precio");
            int cantidad = (int) producto.get("cantidad");
            total += precio * cantidad;
        }

        Map<String, Object> factura = new HashMap<>();
        factura.put("productos", productos);
        factura.put("total", total);

        return factura;
    }
}
