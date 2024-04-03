package tacos.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.entity.Taco;
import tacos.repository.TacoRepository;
import tacos.service.TacoService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TacoServiceImpl implements TacoService {

    private final TacoRepository tacoRepository;

    @Transactional
    public Taco createTaco(Taco taco){
        return tacoRepository.save(taco);
    }

    public Optional<Taco> findTacoById(Long tacoId){
        return tacoRepository.findById(tacoId);
    }

    public Iterable<Taco> findRecentTacos(int pageNumber, int pageSize, Sort sort){
        PageRequest page = PageRequest.of(pageNumber, pageSize, sort);
        return tacoRepository.findAll(page).getContent();
    }
}
