package rosa.victor.pruebainditex.adapter.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FindPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindPrice_WithSpecific_2020_06_14_10_00_00() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_14_16_00_00() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_14_21_00_00() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_15_10_00_00() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_16_21_00_00() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }
}
