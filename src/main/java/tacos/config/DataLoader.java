package tacos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tacos.dto.UserDto;
import tacos.entity.Ingredient;
import tacos.entity.Taco;
import tacos.entity.Type;
import tacos.repository.IngredientRepository;
import tacos.service.TacoService;
import tacos.service.UserService;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner run (IngredientRepository ingredientRepository, UserService userService,
                                  TacoService tacoService){
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
            Ingredient dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
            Ingredient monterreyJack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(dicedTomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(monterreyJack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            userService.create(new UserDto(
                    "user", "user", "Test User", "Test street",
                    "Test city", "Test state", "Test zip", "Test phone"
            ));

            tacoService.create(Taco.builder()
                    .name("Carnivore")
                    .ingredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar))
                    .build());

            tacoService.create(Taco.builder()
                    .name("Bovine Bounty")
                    .ingredients(Arrays.asList(cornTortilla, groundBeef, cheddar, monterreyJack, sourCream))
                    .build());

            tacoService.create(Taco.builder()
                    .name("Veg-Out")
                    .ingredients(Arrays.asList(flourTortilla, cornTortilla, dicedTomatoes, lettuce, salsa))
                    .build());
        };
    }
}