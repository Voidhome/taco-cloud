package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entity.TacoOrder;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
}
