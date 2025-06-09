package org.ub.dapp02practica04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ub.dapp02practica04.dto.ProductDto;
import org.ub.dapp02practica04.models.Producto;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public Iterable<Producto> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Producto> addProduct(@RequestBody ProductDto dto) {

        Producto product = new Producto();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        productRepository.save(product);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable UUID id, @RequestBody ProductDto dto) {
            Optional<Producto> productoOptional = productRepository.findById(id);
            if (productoOptional.isPresent()) {
                productoOptional.get().setName(dto.getName());
                productoOptional.get().setPrice(dto.getPrice());
              return  new ResponseEntity<>(productRepository.save(productoOptional.get()), HttpStatus.OK);

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProduct(@PathVariable UUID id) {
        Optional<Producto> productoOptional = productRepository.findById(id);
        if (productoOptional.isPresent()) {
            productRepository.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
