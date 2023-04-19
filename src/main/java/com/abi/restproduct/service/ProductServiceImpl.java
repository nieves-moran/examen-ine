package com.abi.restproduct.service;

import com.abi.restproduct.entity.Product;
import com.abi.restproduct.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> read() {
        log.info("Se leen todos los productos.");
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product){
        return productRepository.save(product); 
    }

    @Override
    public Optional<Product> delete(int productId){
        Optional<Product> optProd = productRepository.findById(productId); 
        if(optProd.isPresent()){
            productRepository.deleteById(productId);
        }
        return optProd;
    }

    @Override
    public Optional<Product> update(Product product){
        Optional<Product> optProd = productRepository.findById(product.getId()); 
        if(optProd.isPresent()){
            productRepository.save(product); 
        }
        return optProd; 
    }

    @Override
    public Optional<Product> updateName(int id,String name){
        Optional<Product> optProd = productRepository.findById(id); 
        if(optProd.isPresent()){
            Product product= optProd.get(); 
            product.setName(name);
            productRepository.save(product); 
        }
        return optProd; 
       
    }

    @Override
    public Optional<Product> updateDescription(int id,String description){
        Optional<Product> optProd = productRepository.findById(id); 
        if(optProd.isPresent()){
            Product product= optProd.get(); 
            product.setDescription(description);
            productRepository.save(product); 
        }
        return optProd; 
       
    }

    @Override
    public Optional<Product> updatePrice(int id,double price){
        Optional<Product> optProd = productRepository.findById(id); 
        if(optProd.isPresent()){
            Product product= optProd.get(); 
            product.setPrice(price);
            productRepository.save(product); 
        }
        return optProd; 
       
    }

    @Override
    public Optional<Product> updateQuantity(int id,int quantity){
        Optional<Product> optProd = productRepository.findById(id); 
        if(optProd.isPresent()){
            Product product= optProd.get(); 
            product.setQuantity(quantity);
            productRepository.save(product); 
        }
        return optProd; 
       
    }
}
    
