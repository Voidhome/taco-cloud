package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
