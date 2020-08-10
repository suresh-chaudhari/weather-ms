package com.loyalty.one.rest;

import com.loyalty.one.constants.ErrorResponseCodes;
import com.loyalty.one.exceptions.ServiceException;
import com.loyalty.one.model.dao.Product;
import com.loyalty.one.model.response.ProductResponse;
import com.loyalty.one.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Suresh Chaudhari
 */
@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductRestController {

    @Autowired
    private ProductService productService;

    /**
     * To insert product
     */
    @PostMapping(value = "/product")
    public ProductResponse saveProduct(@RequestBody Product product) {

        validateProduct(product);
        return productService.persistProduct(product);
    }


    /**
     * To fetch products
     */
    @GetMapping(value = "/product")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    /**
     * Delete product by product id
     */
    @DeleteMapping(value = "/product/{id}")
    public ProductResponse deleteProduct(@PathVariable int id) {

        return productService.deleteProductById(id);
    }

    /**
     * Update product by product id
     */
    @PutMapping(value = "/product/{id}")
    public ProductResponse updateProduct(@PathVariable int id, @RequestBody Product product) {

        validateProduct(product);
        return productService.updateProductById(id, product);
    }

    /**
     * It validates product name and quantity
     */
    private void validateProduct(Product product) {
        if (StringUtils.isBlank(product.getProductName()) || product.getQuantity() <= 0) {
            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST);
        }
    }
}