package business_layer.entity;

import java.util.List;

public class Drink {
    private String code;
    private String name;
    private List<Ingredient> ingredients;


    public Drink() {
    }


    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "{" +
            " code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", ingredients='" + getIngredients() + "'" +
            "}";
    }

}
