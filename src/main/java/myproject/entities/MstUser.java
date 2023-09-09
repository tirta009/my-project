package myproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_user")
public class MstUser extends BaseEntity{

    private String username;

    private String password;;

    private String email;

    @OneToMany(mappedBy = "mstUser")
    private List<TransactionConcert> transactionConcerts;

}
