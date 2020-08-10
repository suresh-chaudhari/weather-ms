package com.loyalty.one.service;

import com.loyalty.one.model.dao.Product;
import com.loyalty.one.model.response.ProductResponse;
import com.loyalty.one.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void persistProduct() {

        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        when(productRepository.save(any())).thenReturn(any());

        ProductResponse productResponse = productService.persistProduct(product);
        assertEquals("Congratulations ! Data inserted successfully.", productResponse.getMessage());
    }

    @Test
    public void getAllProducts() {

        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        Product product1 = new Product();
        product1.setProductName("Mouse");
        product1.setQuantity(4);

        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(product);
        expectedProductList.add(product1);

        when(productRepository.findAll()).thenReturn(expectedProductList);

        List<Product> actualProductList = productService.getAllProducts();
        assertEquals(expectedProductList.get(0).getProductName(), actualProductList.get(0).getProductName());
        assertEquals(expectedProductList.get(0).getQuantity(), actualProductList.get(0).getQuantity());
        assertEquals(expectedProductList.get(1).getProductName(), actualProductList.get(1).getProductName());
        assertEquals(expectedProductList.get(1).getQuantity(), actualProductList.get(1).getQuantity());
    }

    @Test
    public void deleteProductById() {

        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        doNothing().when(productRepository).deleteById(any());

        ProductResponse productResponse = productService.deleteProductById(1);
        assertEquals("Congratulations ! Data deleted successfully.", productResponse.getMessage());
    }

    @Test
    public void updateProductById() {

        Product product = new Product();
        product.setProductName("Keyboard-Updated");
        product.setQuantity(3);

        doNothing().when(productRepository).updateProduct(anyString(), anyInt(), anyInt());

        ProductResponse productResponse = productService.updateProductById(1, product);
        assertEquals("Congratulations ! Data updated successfully.", productResponse.getMessage());
    }
}
