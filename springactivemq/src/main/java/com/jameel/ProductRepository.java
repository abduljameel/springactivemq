package com.jameel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
@Repository
public interface ProductRepository
        extends JpaRepository<OrderedProduct, Long> {
 
}

