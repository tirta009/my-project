package myproject.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import myproject.dtos.ApiResponse;
import myproject.dtos.TransactionConcertRequest;
import myproject.dtos.TransactionConcertResponse;
import myproject.repositories.TransactionConcertRepository;
import myproject.services.TransactionConcertService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionConcertService transactionConcertService;

    @Autowired
    private TransactionConcertRepository transactionConcertRepository;

    @Test
    void submitMultipleTransaction() throws InterruptedException {

        Thread thread1 = new Thread(() -> {

            TransactionConcertRequest transactionConcertRequest = new TransactionConcertRequest();
            transactionConcertRequest.setConcertDetailId(Long.valueOf(62));
            transactionConcertRequest.setUserId(Long.valueOf(1));
            transactionConcertRequest.setTicketAmount(Long.valueOf(2));
            transactionConcertRequest.setTotalPayment(20000000.00);
            transactionConcertRequest.setMethodPayment("Transfer");

            try {
                mockMvc.perform(
                        post("/api/transaction")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionConcertRequest))
                ).andDo(result -> {
                    ApiResponse apiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ApiResponse>() {
                    });

                    System.out.println(apiResponse.getData() + Thread.currentThread().getName());
                    System.out.println(apiResponse.getError() + Thread.currentThread().getName());

                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        Thread thread2 = new Thread(() -> {

            TransactionConcertRequest transactionConcertRequest = new TransactionConcertRequest();
            transactionConcertRequest.setConcertDetailId(Long.valueOf(42));
            transactionConcertRequest.setUserId(Long.valueOf(2));
            transactionConcertRequest.setTicketAmount(Long.valueOf(2));
            transactionConcertRequest.setTotalPayment(20000000.00);
            transactionConcertRequest.setMethodPayment("Transfer");

            try {
                mockMvc.perform(
                        post("/api/transaction")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionConcertRequest))
                ).andDo(result -> {
                    ApiResponse apiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ApiResponse>() {
                    });

                    System.out.println(apiResponse.getData() + Thread.currentThread().getName());
                    System.out.println(apiResponse.getError() + Thread.currentThread().getName());

                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {

            TransactionConcertRequest transactionConcertRequest = new TransactionConcertRequest();
            transactionConcertRequest.setConcertDetailId(Long.valueOf(42));
            transactionConcertRequest.setUserId(Long.valueOf(3));
            transactionConcertRequest.setTicketAmount(Long.valueOf(2));
            transactionConcertRequest.setTotalPayment(20000000.00);
            transactionConcertRequest.setMethodPayment("Transfer");

            try {
                mockMvc.perform(
                        post("/api/transaction")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(transactionConcertRequest))
                ).andDo(result -> {
                    ApiResponse apiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ApiResponse>() {
                    });

                    System.out.println(apiResponse.getData() + Thread.currentThread().getName());
                    System.out.println(apiResponse.getError() + Thread.currentThread().getName());

                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        thread1.setName("User 1");
        thread2.setName("User 2");
        thread3.setName("User 3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();


    }

    @Test
    void submitTransactionSuccess() throws Exception {

        TransactionConcertRequest transactionConcertRequest = new TransactionConcertRequest();
        transactionConcertRequest.setConcertDetailId(Long.valueOf(61));
        transactionConcertRequest.setUserId(Long.valueOf(1));
        transactionConcertRequest.setTicketAmount(Long.valueOf(1));
        transactionConcertRequest.setTotalPayment(10000000.00);
        transactionConcertRequest.setMethodPayment("Transfer");

        mockMvc.perform(
                post("/api/transaction")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionConcertRequest))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<TransactionConcertResponse> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );

            Assertions.assertNotNull(apiResponse.getData());
            Assertions.assertTrue(transactionConcertRepository.existsById(apiResponse.getData().getId()));
        });
    }

    @Test
    void submitTransactionBadRequest() throws Exception {

        TransactionConcertRequest transactionConcertRequest = new TransactionConcertRequest();
        transactionConcertRequest.setConcertDetailId(Long.valueOf(9999));
        transactionConcertRequest.setUserId(Long.valueOf(99));
        transactionConcertRequest.setTicketAmount(Long.valueOf(10));
        transactionConcertRequest.setTotalPayment(100000.00);
        transactionConcertRequest.setMethodPayment("Transfer");

        mockMvc.perform(
                post("/api/transaction")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionConcertRequest))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            ApiResponse<TransactionConcertResponse> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );

            Assertions.assertNotNull(apiResponse.getError());
        });
    }

    @Test
    void payTransactionSuccess() throws Exception {
        mockMvc.perform(
                put("/api/transaction/pay/34")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<TransactionConcertResponse> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {
            });

            Assertions.assertNotNull(apiResponse.getData());
            Assertions.assertEquals("Paid", apiResponse.getData().getStatusPayment());
        });

    }

    @Test
    void payTransactionBadRequest() throws Exception {
        mockMvc.perform(
                put("/api/transaction/cancel/99")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            ApiResponse<TransactionConcertResponse> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {
            });
            Assertions.assertNotNull(apiResponse.getError());
        });
    }

    @Test
    void cancelTransactionSuccess() throws Exception {

        mockMvc.perform(
                put("/api/transaction/cancel/35")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            ApiResponse<TransactionConcertResponse> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );

            Assertions.assertNotNull(apiResponse.getData());
            Assertions.assertEquals("Cancelled", apiResponse.getData().getStatusPayment());
        });

    }

    @Test
    void cancelTransactionBadRequest() throws Exception {
        mockMvc.perform(
                put("/api/transaction/cancel/99")
        ).andExpectAll(
            status().isBadRequest()
        ).andDo(result -> {
            ApiResponse<TransactionConcertResponse> apiResponse = objectMapper.readValue(
                    result.getResponse().getContentAsString(),
                    new TypeReference<>() {}
            );
            Assertions.assertNotNull(apiResponse.getError());
        });
    }
}