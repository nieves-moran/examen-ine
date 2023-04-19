package com.abi.restproduct.service;

import com.abi.restproduct.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> read();
    Product create(Product product); 
    Optional<Product> delete(int productId); 
    Optional<Product> update(Product product); 
    Optional<Product> updateName(int id,String name); 
    Optional<Product> updateDescription(int id,String description); 
    Optional<Product> updatePrice(int id,double price); 
    Optional<Product> updateQuantity(int id,int quantity); 
}
