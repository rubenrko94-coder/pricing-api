package com.pricing.application;

import com.pricing.application.service.impl.PriceServiceImpl;
import com.pricing.application.dto.PriceResult;
import com.pricing.domain.entities.PriceEntity;
import com.pricing.domain.repository.PriceJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceTest {

    @Mock
    PriceJpaRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl getApplicablePrice;

    @Test
    void priceFound() {
        LocalDateTime at = LocalDateTime.of(2020,6,14,16,0,0);
        PriceEntity price = new PriceEntity(10L, 1, at.minusHours(1), at.plusHours(1), 2, 35455L, 1, new BigDecimal("25.45"), "EUR");
        when(priceRepository.findTopApplicable(at, 35455L, 1)).thenReturn(Optional.of(price));

        PriceResult result = getApplicablePrice.execute(at, 35455L, 1);

        assertThat(result.getPriceList()).isEqualTo(2);
        assertThat(result.getPrice()).isEqualByComparingTo("25.45");
        verify(priceRepository).findTopApplicable(at, 35455L, 1);
    }

    @Test
    void priceNotFound() {
        when(priceRepository.findTopApplicable(any(), any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getApplicablePrice.execute(LocalDateTime.now(), 35455L, 1))
                .isInstanceOf(ResponseStatusException.class);
    }
}