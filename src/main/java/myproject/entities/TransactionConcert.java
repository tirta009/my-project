package myproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_concert")
public class TransactionConcert extends BaseEntity{

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "ticket_amount")
    private Long ticketAmount;

    @Column(name = "total_payment")
    private Double totalPayment;

    @Column(name = "status_payment")
    private String statusPayment;

    @Column(name = "method_payment")
    private String methodPayment;

    @ManyToOne
    @JoinColumn(name = "concert_detail_id", referencedColumnName = "id")
    private ConcertDetail concertDetail;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MstUser mstUser;

}
