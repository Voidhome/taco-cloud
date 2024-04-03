package tacos.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.entity.Ingredient;
import tacos.service.IngredientService;

@RestController
@RequestMapping(path = "/api/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IngredientRestController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<Ingredient> findIngredientById(String ingredientId){
        return ingredientService.findIngredientById(ingredientId)
                .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Iterable<Ingredient>> findAllIngredients(){
        return new ResponseEntity<>(ingredientService.findAllIngredients(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> createIngredient (@RequestBody Ingredient ingredient){
        return new ResponseEntity<>(ingredientService.createIngredient(ingredient), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{ingredientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateIngredient(@PathVariable("ingredientId") String ingredientId,
                                                 @RequestBody Ingredient ingredient){
        ingredientService.updateIngredient(ingredientId, ingredient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient (@PathVariable("ingredientId") String ingredientId) {
        try {
            ingredientService.deleteIngredient(ingredientId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
