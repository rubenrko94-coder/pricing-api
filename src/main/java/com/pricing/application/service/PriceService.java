package com.pricing.application.service;

import com.pricing.application.dto.PriceResult;
import java.time.LocalDateTime;

public interface PriceService {

    PriceResult execute(LocalDateTime applicationDate, Long productId, Integer brandId);
}
