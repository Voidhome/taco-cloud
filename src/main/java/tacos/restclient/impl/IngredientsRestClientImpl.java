package tacos.restclient.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import tacos.entity.Ingredient;
import tacos.restclient.exception.BadRequestException;
import tacos.restclient.IngredientsRestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
//@Service
@RequiredArgsConstructor
public class IngredientsRestClientImpl implements IngredientsRestClient {

    private static final ParameterizedTypeReference<List<Ingredient>> INGREDIENTS_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {};

    private final RestClient restClient;

    @Override
    public List<Ingredient> findAllIngredients() {
        return restClient.get()
                .uri("http://localhost:8080/ingredients")
                .retrieve()
                .body(INGREDIENTS_TYPE_REFERENCE);
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        try {
            return restClient.post()
                    .uri("http://localhost:8080/ingredients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ingredient)
                    .retrieve()
                    .body(Ingredient.class);
        }catch (HttpClientErrorException.BadRequest exception){
            var problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<Ingredient> findIngredientById(String ingredientId) {
        try {
            return Optional.ofNullable(restClient.get()
                    .uri("http://localhost:8080/ingredients/{id}", ingredientId)
                    .retrieve()
                    .body(Ingredient.class));
        }catch (HttpClientErrorException.NotFound exception){
            return Optional.empty();
        }
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        try {
            restClient.put()
                    .uri("http://localhost:8080/ingredients/{id}", ingredient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Ingredient.class)
                    .retrieve()
                    .toBodilessEntity();
        }catch (HttpClientErrorException.BadRequest exception){
            var problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        try {
            restClient.delete()
                    .uri("http://localhost:8080/ingredients/{id}", ingredient.getId())
                    .retrieve()
                    .toBodilessEntity();
        }catch (HttpClientErrorException.NotFound exception){
            throw new NoSuchElementException(exception);
        }
    }
}
