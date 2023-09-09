package myproject.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.entities.ConcertDetail;
import myproject.entities.ConcertHeader;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcertHeaderResponse {

    private Long id;
    private String concertName;
    private String concertPlace;
    private String description;
    private List<ConcertDetailResponse> concertDetail;

    public static ConcertHeaderResponse entityToDto(ConcertHeader entity){

        ConcertHeaderResponse response = new ConcertHeaderResponse();
        response.setId(entity.getId());
        response.setConcertName(entity.getConcertName());
        response.setConcertPlace(entity.getConcertPlace());
        response.setDescription(entity.getDescription());
        response.setConcertDetail(ConcertDetailResponse.entitiesToDto(entity.getConcertDetails()));

        return response;
    }

}
