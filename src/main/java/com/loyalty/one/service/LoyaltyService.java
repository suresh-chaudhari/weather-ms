package com.loyalty.one.service;

import com.loyalty.one.model.dao.LoyaltyDao;
import com.loyalty.one.model.response.Loyalty;
import com.loyalty.one.repository.LoyaltyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * @author Suresh Chaudhari
 */
@Service
@Slf4j
public class LoyaltyService {

    @Autowired
    private LoyaltyRepository loyaltyRepository;

    /**
     * It persist the Loyalty data in database
     *
     * @return
     */
    public Loyalty saveLoyaltyData(Loyalty loyalty) {

        LoyaltyDao loyaltyDao = new LoyaltyDao();
        loyaltyDao.setText(loyalty.getText());

        loyaltyRepository.save(loyaltyDao);
        log.info("Web form text data store in database");


        return createResponse(loyaltyDao, loyalty);
    }

    private Loyalty createResponse(LoyaltyDao loyaltyDao, Loyalty loyalty) {
        loyalty.setId(loyaltyDao.getId());
        loyalty.setDate(new Date(loyaltyDao.getTime()).toString());
        return loyalty;
    }


}
