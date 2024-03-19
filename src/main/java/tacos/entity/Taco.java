package tacos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Taco implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients = new ArrayList<>();

    private LocalDateTime createdAt;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
}