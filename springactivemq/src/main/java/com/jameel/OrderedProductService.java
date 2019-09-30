package com.jameel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderedProductService {
     
    @Autowired
    ProductRepository repository;
     
    public OrderedProduct getProductById(Long id) throws RecordNotFoundException
    {
        Optional<OrderedProduct> product = repository.findById(id);
         
        if(product.isPresent()) {
            return product.get();
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }
    
    public OrderedProduct saveProduct(OrderedProduct entity) throws RecordNotFoundException
    {
        Optional<OrderedProduct> product = repository.findById(entity.getId());
         
        if(product.isPresent())
        {
            OrderedProduct newEntity = product.get();
            newEntity.setProductName(entity.getProductName());
            newEntity.setPrdCount(entity.getPrdCount());
            newEntity.setPrice(entity.getPrice());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
        //try {
        		entity = repository.save(entity);
//        	}
//        	catch()
//        	{
//        		DriverManager.registerDriver(new org.h2.Driver());
//                Connection c = DriverManager.getConnection("jdbc:h2:mem:test");
//                PreparedStatement stmt = c.prepareStatement("CREATE TABLE PERSON (ID INT PRIMARY KEY, FIRSTNAME VARCHAR(64), LASTNAME VARCHAR(64))");
//                stmt.execute();
//                stmt.close();
//                c.close();
//        	}
        		
//        		DROP TABLE IF EXISTS ORDER_PRODUCTS;
//        		 
//        		CREATE TABLE ORDER_PRODUCTS_1 (
//        		  id INT AUTO_INCREMENT  PRIMARY KEY,
//        		  product_name VARCHAR(250) NOT NULL,
//        		  product_count INT NOT NULL,
//        		  price INT DEFAULT NULL
//        		);
            return entity;
        }
    }
     

}