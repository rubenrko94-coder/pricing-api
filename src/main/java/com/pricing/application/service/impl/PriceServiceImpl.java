package com.pricing.application.service.impl;

import com.pricing.application.service.PriceService;
import com.pricing.application.dto.PriceResult;
import com.pricing.domain.entities.PriceEntity;
import com.pricing.domain.repository.PriceJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {
    private static final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceJpaRepository priceRepository;

    public PriceServiceImpl(PriceJpaRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceResult execute(LocalDateTime applicationDate, Long productId, Integer brandId) {
        log.info("Searching applicable price for productId={}, brandId={}, at={}", productId, brandId, applicationDate);
        PriceEntity price = priceRepository.findTopApplicable(applicationDate, productId, brandId)
                .orElseThrow(() -> {
                        log.info("No applicable price found for productId={}, brandId={}, at={}", productId, brandId, applicationDate);
                        return new ResponseStatusException(HttpStatus.NOT_FOUND, "No applicable price found");
                });

        log.info("Applicable price found: priceList={}, price={}, start={}, end={}",
                price.getPriceList(), price.getPrice(), price.getStartDate(), price.getEndDate());

        return new PriceResult(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );

    }
}