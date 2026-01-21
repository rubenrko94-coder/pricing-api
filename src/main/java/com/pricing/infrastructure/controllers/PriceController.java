package com.pricing.infrastructure.controllers;

import com.pricing.application.service.PriceService;
import com.pricing.application.dto.PriceResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@Validated
public class PriceController {

    private final PriceService getApplicablePrice;

    public PriceController(PriceService getApplicablePrice) {
        this.getApplicablePrice = getApplicablePrice;
    }

    @Operation(
            summary = "Get applicable price for a product and brand at a given date",
            description = "Returns the price row with highest priority",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Price found",
                            content = @Content(schema = @Schema(implementation = PriceResult.class))),
                    @ApiResponse(responseCode = "404", description = "No applicable price found"),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    @GetMapping
    public ResponseEntity<PriceResult> getApplicablePrice(
            @RequestParam("applicationDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @Parameter(description = "ISO-8601 LocalDateTime, e.g. 2020-06-14T10:00:00")
            LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Integer brandId
    ) {
        return ResponseEntity.ok(getApplicablePrice.execute(applicationDate, productId, brandId));
    }
}