
package tacos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.dto.UserDto;
import tacos.entity.TacoOrder;

@Repository
public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

    Page<TacoOrder> findByUserOrderByPlacedAtDesc(UserDto user, Pageable pageable);
}
