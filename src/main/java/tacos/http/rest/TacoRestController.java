package tacos.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Taco;
import tacos.service.TacoService;

@RestController
@RequestMapping(path = "/api/tacos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TacoRestController {

    private final TacoService tacoService;

    @GetMapping("/{tacoId}")
    public ResponseEntity<Taco> findTacoById(@PathVariable("tacoId") Long tacoId){
        return tacoService.findTacoById(tacoId)
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "recent")
    public ResponseEntity<Iterable<Taco>> recentTaco(){
        return new ResponseEntity<>(tacoService.findRecentTacos(0, 12,
                Sort.by("createdAt").descending()), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Taco> createTaco(@RequestBody Taco taco){
        return new ResponseEntity<>(tacoService.createTaco(taco), HttpStatus.CREATED);
    }
}
