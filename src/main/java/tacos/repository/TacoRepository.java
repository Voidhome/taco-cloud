package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.entity.Taco;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {
}
