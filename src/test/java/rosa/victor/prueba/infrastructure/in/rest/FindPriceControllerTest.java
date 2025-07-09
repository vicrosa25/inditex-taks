package rosa.victor.prueba.infrastructure.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.hasKey;
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

    @Test
    void testFindPrice_NotFoundPrice() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2019, 1, 1, 10, 0, 0); // Date before any price record
        int productId = 35455;
        int brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", Integer.toString(productId))
                .param("brandId", Integer.toString(brandId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    void testFindPrice_MissingRequiredArgument() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        int productId = 35455;
        // brandId is missing

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", applicationDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .param("productId", Integer.toString(productId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.errors", hasKey("brandId")));
    }

    @Test
    void testFindPrice_WrongParameterFormat() throws Exception {
        // Given
        String invalidDate = "2020/06/14 10:00:00"; // Truly invalid format for ISO_DATE_TIME
        int productId = 35455;
        int brandId = 1;

        // When & Then
        mockMvc.perform(post("/prices")
                .param("applicationDate", invalidDate)
                .param("productId", Integer.toString(productId))
                .param("brandId", Integer.toString(brandId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}
