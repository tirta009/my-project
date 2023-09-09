package myproject.repositories;

import myproject.entities.ConcertHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConcertHeaderRepository extends JpaRepository<ConcertHeader, Long> {

    @Query(value = "SELECT header FROM ConcertHeader header JOIN header.concertDetails detail " +
                   "WHERE (:concertName IS NULL OR header.concertName ilike %:concertName%) " +
                   "GROUP BY header.id " +
                   "having sum(detail.ticketsAmount) > 0 " +
                   "ORDER BY header.id asc")
    Page<ConcertHeader> findConcert(@Param("concertName") String concertName,
                                      Pageable pageable);

}
