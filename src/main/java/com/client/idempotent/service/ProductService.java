package com.client.idempotent.service;

import com.client.idempotent.model.Product;
import com.client.idempotent.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public Optional<Product> findByIdProduct(Long id) {
        return productRepository.findById(id);
    }

   public List<Product> findAllProducts() {
        return productRepository.findAll();
   }

   public Product createdProduct(Product product) {
        return productRepository.save(product);
   }

   public Product updateProduct(Product product) {
        if(findByIdProduct(product.getId()).isPresent()){
            return productRepository.save(product);
        }
        else
            throw new RuntimeException(); // criar uma exeção personalizada
   }


   public void deleteProduct(Long id) {
       Product product =  findByIdProduct(id).get();
       if(productRepository.existsById(id)){
           productRepository.delete(product);
       }
       else {
           throw new RuntimeException(); // cria uma exeção
       }



   }
}
