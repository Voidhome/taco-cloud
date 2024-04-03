package tacos.service;

import tacos.entity.Ingredient;
import tacos.entity.Type;

import java.util.List;
import java.util.Optional;


public interface IngredientService {

    List<Ingredient> findAllIngredients();

    Optional<Ingredient> findIngredientById(String ingredientId);

    Ingredient createIngredient(Ingredient ingredient);

    void updateIngredient(String ingredientId, Ingredient ingredient);

    void deleteIngredient(String ingredientId);

    Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type);
}