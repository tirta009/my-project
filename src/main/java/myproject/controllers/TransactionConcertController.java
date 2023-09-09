package myproject.controllers;

import myproject.dtos.ApiResponse;
import myproject.dtos.TransactionConcertRequest;
import myproject.dtos.TransactionConcertResponse;
import myproject.entities.TransactionConcert;
import myproject.services.TransactionConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class TransactionConcertController {

    @Autowired
    private TransactionConcertService transactionConcertService;

    private final Lock lock = new ReentrantLock();

    @PostMapping(value = "api/transaction",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<TransactionConcertResponse> submitTransactionConcert(@RequestBody TransactionConcertRequest request) {

        try{
            lock.lock();
            TransactionConcert entity = transactionConcertService.submitTransactionConcert(request);
            TransactionConcertResponse response = TransactionConcertResponse.entityToDto(entity);
            return ApiResponse.<TransactionConcertResponse>builder().data(response).build();
        } finally {
            lock.unlock();
        }

    }

    @PutMapping(value = "api/transaction/pay/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<TransactionConcertResponse> payTransactionConcert(@PathVariable Long id) {

        TransactionConcert entity = transactionConcertService.payTransactionConcert(id);
        TransactionConcertResponse response = TransactionConcertResponse.entityToDto(entity);

        return ApiResponse.<TransactionConcertResponse>builder().data(response).build();


    }

    @PutMapping(value = "api/transaction/cancel/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<TransactionConcertResponse> cancelTransactionConcert(@PathVariable Long id) {

        TransactionConcert entity = transactionConcertService.cancelTransactionConcert(id);
        TransactionConcertResponse response = TransactionConcertResponse.entityToDto(entity);

        return ApiResponse.<TransactionConcertResponse>builder().data(response).build();

    }

}
