package com.productos.ecommerce.spring_ecommerce.controller;



import com.productos.ecommerce.spring_ecommerce.model.Producto;
import com.productos.ecommerce.spring_ecommerce.model.Usuario;
import com.productos.ecommerce.spring_ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") //  permite que el frontend pueda acceder

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productoService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("precio") double precio
    ) {
        Usuario usuario = new Usuario();
        usuario.setId(1); // ID del usuario existente en la base de datos

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion); // usa acento si tu atributo lo tiene
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);
        producto.setUsuario(usuario);

        productoService.save(producto);

        return ResponseEntity.ok("Producto guardado correctamente");
    }

}
