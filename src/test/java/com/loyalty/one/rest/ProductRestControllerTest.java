package com.loyalty.one.rest;

import com.loyalty.one.model.dao.Product;
import com.loyalty.one.model.response.ProductResponse;
import com.loyalty.one.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductRestController productRestController;

    @Mock
    private ProductService productService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(productRestController)
                .build();
    }

    @Test
    public void saveProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        ProductResponse expectedProductResponse = new ProductResponse();
        expectedProductResponse.setMessage("Congratulations ! Data inserted successfully.");

        when(productService.persistProduct(any())).thenReturn(expectedProductResponse);

        MvcResult mvcResult = this.mockMvc.perform(post("/v1/api/product")
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ProductResponse actualProductResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), ProductResponse.class);
        assertEquals(expectedProductResponse.getMessage(), actualProductResponse.getMessage());
    }

    @Test
    public void saveProductWhenProductNameIsEmpty() throws Exception {
        expectedException.expect(NestedServletException.class);
        expectedException.expectMessage("Please Enter Product and Quantity");

        Product product = new Product();
        product.setProductName(""); //empty product name
        product.setQuantity(2);

        ProductResponse expectedProductResponse = new ProductResponse();
        expectedProductResponse.setMessage("Congratulations ! Data inserted successfully.");

        when(productService.persistProduct(any())).thenReturn(expectedProductResponse);

        this.mockMvc.perform(post("/v1/api/product")
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveProductWhenQuantityIsZeroOrEmpty() throws Exception {
        expectedException.expect(NestedServletException.class);
        expectedException.expectMessage("Please Enter Product and Quantity");

        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(0);//set quantity zero for validation

        ProductResponse expectedProductResponse = new ProductResponse();
        expectedProductResponse.setMessage("Congratulations ! Data inserted successfully.");

        when(productService.persistProduct(any())).thenReturn(expectedProductResponse);

        this.mockMvc.perform(post("/v1/api/product")
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllProducts() throws Exception {
        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        Product product1 = new Product();
        product1.setProductName("Mouse");
        product1.setQuantity(4);

        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(product);
        expectedProductList.add(product1);

        when(productService.getAllProducts()).thenReturn(expectedProductList);

        MvcResult mvcResult = this.mockMvc.perform(get("/v1/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Product> actualProductList = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>(){});

        assertEquals(expectedProductList.get(0).getProductName(), actualProductList.get(0).getProductName());
        assertEquals(expectedProductList.get(0).getQuantity(), actualProductList.get(0).getQuantity());
        assertEquals(expectedProductList.get(1).getProductName(), actualProductList.get(1).getProductName());
        assertEquals(expectedProductList.get(1).getQuantity(), actualProductList.get(1).getQuantity());
    }

    @Test
    public void deleteProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        ProductResponse expectedProductResponse = new ProductResponse();
        expectedProductResponse.setMessage("Congratulations ! Data deleted successfully.");

        when(productService.deleteProductById(anyInt())).thenReturn(expectedProductResponse);

        MvcResult mvcResult = this.mockMvc.perform(delete("/v1/api/product/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ProductResponse actualProductResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), ProductResponse.class);
        assertEquals(expectedProductResponse.getMessage(), actualProductResponse.getMessage());
    }

    @Test
    public void updateProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(2);

        ProductResponse expectedProductResponse = new ProductResponse();
        expectedProductResponse.setMessage("Congratulations ! Data updated successfully.");

        when(productService.updateProductById(anyInt(), any())).thenReturn(expectedProductResponse);

        MvcResult mvcResult = this.mockMvc.perform(put("/v1/api/product/{id}", 1)
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ProductResponse actualProductResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), ProductResponse.class);
        assertEquals(expectedProductResponse.getMessage(), actualProductResponse.getMessage());
    }

    @Test
    public void updateProductWhenProductNameIsEmpty() throws Exception {
        expectedException.expect(NestedServletException.class);
        expectedException.expectMessage("Please Enter Product and Quantity");

        Product product = new Product();
        product.setProductName(""); //empty product name
        product.setQuantity(2);

        this.mockMvc.perform(put("/v1/api/product/{id}", 1)
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateProductWhenQuantityIsZeroOrEmpty() throws Exception {
        expectedException.expect(NestedServletException.class);
        expectedException.expectMessage("Please Enter Product and Quantity");

        Product product = new Product();
        product.setProductName("Keyboard");
        product.setQuantity(0);//set quantity zero for validation

        this.mockMvc.perform(put("/v1/api/product/{id}", 1)
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
