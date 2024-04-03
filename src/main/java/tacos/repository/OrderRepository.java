
package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.entity.TacoOrder;

@Repository
public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
}
