package myproject.controllers;

import myproject.dtos.ApiResponse;
import myproject.dtos.ConcertHeaderResponse;
import myproject.dtos.PagingResponse;
import myproject.entities.ConcertHeader;
import myproject.services.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping("/api/concerts")
    public ApiResponse<List<ConcertHeaderResponse>> findConcert(
            @RequestParam(value = "concertName", required = false) String concertName,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Page<ConcertHeaderResponse> concertHeaderResponses =
                concertService.findConcert(concertName, page, size);

        return ApiResponse.<List<ConcertHeaderResponse>>builder()
                .data(concertHeaderResponses.getContent())
                .paging(new PagingResponse(concertHeaderResponses.getNumber(),
                                            concertHeaderResponses.getSize(),
                                            concertHeaderResponses.getTotalPages(),
                                            concertHeaderResponses.getTotalElements()))
                .build();
    }

}
