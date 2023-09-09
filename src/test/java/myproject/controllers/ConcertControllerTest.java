package myproject.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import myproject.dtos.ApiResponse;
import myproject.dtos.ConcertHeaderResponse;
import myproject.entities.ConcertDetail;
import myproject.entities.ConcertHeader;
import myproject.repositories.ConcertDetailRepository;
import myproject.repositories.ConcertHeaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConcertControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ConcertHeaderRepository concertHeaderRepository;

    @Autowired
    ConcertDetailRepository concertDetailRepository;

    @Test
    void searchFound() throws Exception {

        for (int i = 0; i < 20 ; i++) {

            ConcertHeader concertHeader = new ConcertHeader();
            concertHeader.setConcertName("Coldplay " + i);
            concertHeader.setConcertPlace("Jakarta");
            concertHeader.setDescription("Coldplay Tour");

            concertHeader = concertHeaderRepository.save(concertHeader);

            ConcertDetail concertDetail = new ConcertDetail();
            concertDetail.setConcertHeader(concertHeader);
            concertDetail.setConcertDate(new Date());
            concertDetail.setConcertBegTime(LocalDateTime.now());
            concertDetail.setPrice(10000000.00);
            concertDetail.setTicketsAmount(Long.valueOf(1000));
            concertDetailRepository.save(concertDetail);

        }

        mockMvc.perform(
                get("/api/concerts")
                        .queryParam("concertName", "Coldplay")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<List<ConcertHeaderResponse>> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );

            assertEquals(0, apiResponse.getPaging().getCurrentPage());
            assertEquals(10, apiResponse.getPaging().getPageSize());
            assertEquals(2, apiResponse.getPaging().getTotalPages());
            assertEquals(20, apiResponse.getPaging().getTotalRecords());
        });


        mockMvc.perform(
                get("/api/concerts")
                        .queryParam("concertName", "Coldplay")
                        .queryParam("page", "100")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<List<ConcertHeaderResponse>> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );

            assertEquals(100, apiResponse.getPaging().getCurrentPage());
            assertEquals(10, apiResponse.getPaging().getPageSize());
            assertEquals(2, apiResponse.getPaging().getTotalPages());
            assertEquals(20, apiResponse.getPaging().getTotalRecords());
        });

    }

    @Test
    void searchNotFound() throws Exception {
        mockMvc.perform(
                get("/api/concerts")
                        .queryParam("concertName", "ABCD")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<List<ConcertHeaderResponse>> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );
            assertEquals(0, apiResponse.getPaging().getCurrentPage());
            assertEquals(10, apiResponse.getPaging().getPageSize());
            assertEquals(0, apiResponse.getPaging().getTotalPages());
            assertEquals(0, apiResponse.getPaging().getTotalRecords());
        });
    }
}