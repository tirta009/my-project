package myproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.entities.TransactionConcert;
import myproject.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionConcertResponse {

    private Long id;
    private String transactionDate;
    private ConcertDetailResponse concertDetail;
    private MstUserResponse user;
    private Long ticketAmount;
    private Double totalPayment;
    private String statusPayment;
    private String methodPayment;

    public static TransactionConcertResponse entityToDto(TransactionConcert entity){

        TransactionConcertResponse response = new TransactionConcertResponse();
        response.setId(entity.getId());
        response.setTransactionDate(DateUtils.convertDateToString(entity.getTransactionDate(), "dd-MM-yyyy"));
        response.setConcertDetail(ConcertDetailResponse.entityToDto(entity.getConcertDetail()));
        response.setUser(MstUserResponse.entityToDto(entity.getMstUser()));
        response.setTicketAmount(entity.getTicketAmount());
        response.setTotalPayment(entity.getTotalPayment());
        response.setStatusPayment(entity.getStatusPayment());
        response.setMethodPayment(entity.getMethodPayment());

        return response;
    }


}
