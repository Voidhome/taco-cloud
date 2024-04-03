package tacos.restclient;

import tacos.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientsRestClient {

    List<Ingredient> findAllIngredients();

    Ingredient createIngredient(Ingredient ingredient);

    Optional<Ingredient> findIngredientById(String ingredientId);

    void updateIngredient(Ingredient ingredient);

    void deleteIngredient(Ingredient ingredient);
}
