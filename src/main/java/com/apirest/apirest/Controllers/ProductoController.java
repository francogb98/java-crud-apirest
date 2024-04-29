package com.apirest.apirest.Controllers;



import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.apirest.apirest.Repositories.ProductoRepository;
import com.apirest.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts(){
        return productoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro el producto con el id: " + id));
    }
    
    @PostMapping
    public Producto createProduct(@RequestBody Producto producto ){
        return productoRepository.save(producto);
    }
    
    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto productoDetails) {
        System.out.println(id);
        Producto producto = productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro el producto con el id: " + id));
        
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro el producto con el id: " + id));
        productoRepository.delete(producto);

        return "El producto con el ID: "+ id + " fue eliminado correctamente";
    }
}
