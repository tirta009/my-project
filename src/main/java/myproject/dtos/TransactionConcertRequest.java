package myproject.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionConcertRequest {

    @NotBlank
    private Long concertDetailId;

    @NotBlank
    private Long userId;

    @NotBlank
    private Long ticketAmount;

    @NotBlank
    private Double totalPayment;

    @NotBlank
    private String methodPayment;

}
