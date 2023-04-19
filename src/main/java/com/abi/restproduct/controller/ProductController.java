package com.abi.restproduct.controller;

import com.abi.restproduct.entity.Product;
import com.abi.restproduct.model.ProductRequest;
import com.abi.restproduct.service.ProductService;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        String message = "Service Product working";
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> read(){
        List<Product> productList = productService.read();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> create(@RequestBody ProductRequest request){
        String message = "Creating a new Product"; 
        log.info(message);
        Product product = new Product(); 
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product = productService.create(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String message = "Deleting a Product"; 
        log.info(message);
        Optional<Product> optProd = productService.delete(id);
        if(optProd.isPresent()){
            return new ResponseEntity<String>("deleted",HttpStatus.NO_CONTENT); 
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
       
    }
    
    // Este metodo reemplaza el Producto totalmente. Así que se pueden cambiar varios atributos a la vez  
    @PutMapping("")
    public ResponseEntity<Product> update(@RequestBody ProductRequest request){
        String message = "Updating a Product"; 
        log.info(message);
        Product product = new Product(); 
        product.setId(request.getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Optional<Product> optProd = productService.update(product);
        if(optProd.isPresent()){
            return new ResponseEntity<>(optProd.get(),HttpStatus.OK); 
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    // Los siguietes métodos reemplazan información parcial del Producto. 
    // Uno por cada campo. Por ejemplo para cambiar el nombre de el producto 
    // con identificador 10 por "Historia" la ruta sería 
    // /product/name/10/Historia

    @PatchMapping("/name/{id}/{name}")
    public ResponseEntity<Product> updateName(@PathVariable int id,@PathVariable String name){
        String message = "Updating the name of product with id "+ id; 
        log.info(message);
        Optional<Product> optProd = productService.updateName(id, name); 
        if(optProd.isPresent()){
            return new ResponseEntity<>(optProd.get(),HttpStatus.OK); 
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
        }
    }

    @PatchMapping("/description/{id}/{description}")
    public ResponseEntity<Product> updateDescription(@PathVariable int id,@PathVariable String description){
        String message = "Updating the description of product with id "+ id; 
        log.info(message);
        Optional<Product> optProd = productService.updateDescription(id, description); 
        if(optProd.isPresent()){
            return new ResponseEntity<>(optProd.get(),HttpStatus.OK); 
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
        }
    }

    @PatchMapping("/price/{id}/{price}")
    public ResponseEntity<Product> updatePrice(@PathVariable int id,@PathVariable double  price){
        String message = "Updating the price of product with id "+ id; 
        log.info(message);
        Optional<Product> optProd = productService.updatePrice(id, price); 
        if(optProd.isPresent()){
            return new ResponseEntity<>(optProd.get(),HttpStatus.OK);  
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
        }
    }

    @PatchMapping("/quantity/{id}/{quantity}")
    public ResponseEntity<Product> update(@PathVariable int id,@PathVariable int quantity){
        String message = "Updating the quantity of product with id "+ id; 
        log.info(message);
        Optional<Product> optProd = productService.updateQuantity(id, quantity); 
        if(optProd.isPresent()){
            return new ResponseEntity<>(optProd.get(),HttpStatus.OK); 
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND); 
        }
    }

}
