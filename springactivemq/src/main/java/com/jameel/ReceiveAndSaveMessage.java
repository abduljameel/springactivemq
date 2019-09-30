package com.jameel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




 
@Component
public class ReceiveAndSaveMessage {
  
	
    @Autowired
    OrderedProductService service;
	
	private int count = 1;
	
    @JmsListener(destination = "sampleQueue", containerFactory = "myFactory")
    public void receiveMessage(OrderedProduct product) throws RecordNotFoundException {
        System.out.println("<" + count + "> Received <" + product.toString() + "> from Queue");
        count++;
        //    throw new RuntimeException();
        //transactionRepository.save(transaction);
        
        System.out.println("Saving the product in DB ");
        
        OrderedProduct updated = service.saveProduct(product);
        
        System.out.println("Saved the product in DB ");
        
        OrderedProduct retrieved = service.getProductById(updated.getId());
        
        System.out.println("Product retrieved from DB : " + retrieved.toString());
       
        //return new ResponseEntity<OrderedProduct>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    

    
}
