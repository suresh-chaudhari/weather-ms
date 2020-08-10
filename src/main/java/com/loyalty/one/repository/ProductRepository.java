package com.loyalty.one.repository;

import com.loyalty.one.model.dao.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author Suresh Chaudhari
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.productName =:productName, p.quantity=:quantity WHERE p.id=:id")
    public void updateProduct(@Param("productName") String productName, @Param("quantity") int quantity, @Param("id") int id);

}
