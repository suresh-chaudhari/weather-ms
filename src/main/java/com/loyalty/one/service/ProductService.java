package com.loyalty.one.service;

import com.loyalty.one.model.dao.Product;
import com.loyalty.one.model.response.ProductResponse;
import com.loyalty.one.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Suresh Chaudhari
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * It persist the product data in database
     *
     * @return
     */
    public ProductResponse persistProduct(Product product) {

        productRepository.save(product);
        log.info("Persisted product data in database");

        return new ProductResponse("Congratulations ! Data inserted successfully.");
    }

    /**
     * It gives the products data from database
     *
     * @return
     */
    public List<Product> getAllProducts() {

        return (List<Product>) productRepository.findAll();
    }

    /**
     * It persist the product data in database
     *
     * @return
     */
    public ProductResponse deleteProductById(int id) {

        productRepository.deleteById(id);
        log.info("product deleted from database id: {}", id);

        return new ProductResponse("Congratulations ! Data deleted successfully.");
    }

    /**
     * It updates the product data by id in database
     *
     * @return
     */
    public ProductResponse updateProductById(int id, Product product) {

        productRepository.updateProduct(product.getProductName(), product.getQuantity(), id);
        log.info("product update in database by id: {}", id);

        return new ProductResponse("Congratulations ! Data updated successfully.");
    }

}
