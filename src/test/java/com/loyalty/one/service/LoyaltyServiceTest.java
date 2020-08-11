package com.loyalty.one.service;

import com.loyalty.one.repository.LoyaltyRepository;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class LoyaltyServiceTest {

    @InjectMocks
    private LoyaltyService productService;

    @Mock
    private LoyaltyRepository loyaltyRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void persistProduct() {
//
//        Product product = new Product();
//        product.setProductName("Keyboard");
//        product.setQuantity(2);
//
//        when(loyaltyRepository.save(any())).thenReturn(any());
//
//        ProductResponse productResponse = productService.persistProduct(product);
//        assertEquals("Congratulations ! Data inserted successfully.", productResponse.getMessage());
//    }
//
//    @Test
//    public void getAllProducts() {
//
//        Product product = new Product();
//        product.setProductName("Keyboard");
//        product.setQuantity(2);
//
//        Product product1 = new Product();
//        product1.setProductName("Mouse");
//        product1.setQuantity(4);
//
//        List<Product> expectedProductList = new ArrayList<>();
//        expectedProductList.add(product);
//        expectedProductList.add(product1);
//
//        when(loyaltyRepository.findAll()).thenReturn(expectedProductList);
//
//        List<Product> actualProductList = productService.getAllProducts();
//        assertEquals(expectedProductList.get(0).getProductName(), actualProductList.get(0).getProductName());
//        assertEquals(expectedProductList.get(0).getQuantity(), actualProductList.get(0).getQuantity());
//        assertEquals(expectedProductList.get(1).getProductName(), actualProductList.get(1).getProductName());
//        assertEquals(expectedProductList.get(1).getQuantity(), actualProductList.get(1).getQuantity());
//    }
//
//    @Test
//    public void deleteProductById() {
//
//        Product product = new Product();
//        product.setProductName("Keyboard");
//        product.setQuantity(2);
//
//        doNothing().when(loyaltyRepository).deleteById(any());
//
//        ProductResponse productResponse = productService.deleteProductById(1);
//        assertEquals("Congratulations ! Data deleted successfully.", productResponse.getMessage());
//    }
//
//    @Test
//    public void updateProductById() {
//
//        Product product = new Product();
//        product.setProductName("Keyboard-Updated");
//        product.setQuantity(3);
//
//        doNothing().when(loyaltyRepository).updateProduct(anyString(), anyInt(), anyInt());
//
//        ProductResponse productResponse = productService.updateProductById(1, product);
//        assertEquals("Congratulations ! Data updated successfully.", productResponse.getMessage());
//    }
}
