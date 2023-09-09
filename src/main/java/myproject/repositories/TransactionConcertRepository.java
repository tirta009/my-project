package myproject.repositories;

import myproject.entities.TransactionConcert;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionConcertRepository extends JpaRepository<TransactionConcert, Long> {


}
