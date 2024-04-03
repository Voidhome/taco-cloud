package tacos.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.entity.Ingredient;
import tacos.entity.Type;
import tacos.repository.IngredientRepository;
import tacos.service.IngredientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findIngredientById(String ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @Override
    @Transactional
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void updateIngredient(String ingredientId, Ingredient ingredient) {
        var ingredientById = ingredientRepository.findById(ingredientId).get();
        if (ingredient.getName() != null) {
            ingredientById.setName(ingredient.getName());
        }
        if (ingredient.getType() != null) {
            ingredientById.setType(ingredient.getType());
        }
    }

    @Override
    @Transactional
    public void deleteIngredient(String ingredientId) {
        ingredientRepository.deleteById(ingredientId);

    }

    @Override
    public Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}