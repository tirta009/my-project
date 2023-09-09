package myproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.entities.ConcertDetail;
import myproject.utils.DateUtils;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcertDetailResponse {

    private Long id;
    private String concertDate;
    private String concertBegTime;
    private Double price;
    private Long ticketsAmount;

    public static ConcertDetailResponse entityToDto(ConcertDetail entity){

        ConcertDetailResponse response = new ConcertDetailResponse();
        response.setId(entity.getId());
        response.setConcertDate(DateUtils.convertDateToString(entity.getConcertDate(),"dd-MM-yyyy"));
        response.setConcertBegTime(DateUtils.convertLocalDateTimeToString(entity.getConcertBegTime(),"HH:mm"));
        response.setPrice(entity.getPrice());
        response.setTicketsAmount(entity.getTicketsAmount());

        return response;
    }

    public static List<ConcertDetailResponse> entitiesToDto(List<ConcertDetail> entities){

        List<ConcertDetailResponse> responses = new ArrayList<ConcertDetailResponse>();
        for (ConcertDetail entity : entities){
            responses.add(entityToDto(entity));
        }

        return responses;
    }

}
