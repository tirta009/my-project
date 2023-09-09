package myproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "concert_header")
public class ConcertHeader extends BaseEntity{

    @Column(name = "concert_name")
    private String concertName;

    @Column(name = "concert_place")
    private String concertPlace;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "concertHeader")
    private List<ConcertDetail> concertDetails;

}
