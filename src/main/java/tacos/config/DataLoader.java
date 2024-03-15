package tacos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.entity.Ingredient;
import tacos.entity.Type;
import tacos.entity.User;
import tacos.repository.IngredientRepository;
import tacos.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner run (IngredientRepository ingredientRepository, UserRepository userRepository,
                                  PasswordEncoder encoder){
        return args -> {
            ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

            userRepository.save(new User("user", encoder.encode("user"), "Test User",
                    "Test street", "Test city", "Test state", "Test zip", "Test phone"));
        };
    }
}