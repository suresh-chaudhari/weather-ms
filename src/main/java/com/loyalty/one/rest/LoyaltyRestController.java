package com.loyalty.one.rest;

import com.loyalty.one.model.response.Loyalty;
import com.loyalty.one.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Suresh Chaudhari
 */
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class LoyaltyRestController {

    @Autowired
    private LoyaltyService loyaltyService;

    /**
     * To insert product
     */
    @PostMapping(value = "/loyalty")
    public Loyalty saveLoyalty(@RequestBody Loyalty loyalty) {

        return loyaltyService.saveLoyaltyData(loyalty);
    }

//
//    /**
//     * It validates product name and quantity
//     */
//    private void validateProduct(Product product) {
//        if (StringUtils.isBlank(product.getProductName()) || product.getQuantity() <= 0) {
//            throw new ServiceException(ErrorResponseCodes.BAD_REQUEST);
//        }
//    }
}