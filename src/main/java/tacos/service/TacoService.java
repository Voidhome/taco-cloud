package tacos.service;

import org.springframework.data.domain.Sort;
import tacos.entity.Taco;

import java.util.Optional;


public interface TacoService {

    Taco createTaco(Taco taco);

    public Optional<Taco> findTacoById(Long tacoId);

    public Iterable<Taco> findRecentTacos(int pageNumber, int pageSize, Sort sort);
}
