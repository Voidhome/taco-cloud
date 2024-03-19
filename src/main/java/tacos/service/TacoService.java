package tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.entity.Taco;
import tacos.repository.TacoRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TacoService {

    private final TacoRepository tacoRepository;

    @Transactional
    public Taco create (Taco taco){
        return tacoRepository.save(taco);
    }

    public Optional<Taco> findById(Long id){
        return tacoRepository.findById(id);
    }

    public Iterable<Taco> findRecentTacos(int pageNumber, int pageSize, Sort sort){
        PageRequest page = PageRequest.of(pageNumber, pageSize, sort);
        return tacoRepository.findAll(page).getContent();
    }
}
