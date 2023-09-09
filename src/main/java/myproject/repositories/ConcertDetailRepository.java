package myproject.repositories;

import myproject.entities.ConcertDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDetailRepository extends JpaRepository<ConcertDetail, Long> {
}
