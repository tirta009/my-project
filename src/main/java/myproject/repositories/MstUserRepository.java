package myproject.repositories;

import myproject.entities.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstUserRepository extends JpaRepository<MstUser, Long> {
}
