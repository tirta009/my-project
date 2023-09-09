package myproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "concert_detail")
public class ConcertDetail extends BaseEntity{

    @Column(name = "concert_date", unique = true)
    private Date concertDate;

    @Column(name = "concert_beg_time", unique = true)
    private LocalDateTime concertBegTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "tickets_amount")
    private Long ticketsAmount;

    @ManyToOne
    @JoinColumn(name = "concert_header_id", referencedColumnName = "id")
    private ConcertHeader concertHeader;

    @OneToMany(mappedBy = "concertDetail")
    private List<TransactionConcert> transactionConcerts;

}
