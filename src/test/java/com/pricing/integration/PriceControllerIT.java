package com.pricing.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/api/v1/prices";

    @Test
    @DisplayName("Test 1: applicationDate = 2020-06-14T10:00; brandId = 1, productId = 35455")
    void test1() throws Exception {
        mockMvc.perform(get(URL)
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test 2: applicationDate = 2020-06-14T16:00; brandId = 1, productId = 35455")
    void test2() throws Exception {
        mockMvc.perform(get(URL)
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test 3: applicationDate = 2020-06-14T21:00; brandId = 1, productId = 35455")
    void test3() throws Exception {
        mockMvc.perform(get(URL)
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test 4: applicationDate = 2020-06-15T10:00; brandId = 1, productId = 35455")
    void test4() throws Exception {
        mockMvc.perform(get(URL)
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test 5: applicationDate = 2020-06-16T21:00; brandId = 1, productId = 35455")
    void test5() throws Exception {
        mockMvc.perform(get(URL)
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk());
    }
}