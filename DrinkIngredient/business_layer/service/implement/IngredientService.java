package business_layer.service.implement;

import java.util.List;

import business_layer.entity.Ingredient;
import business_layer.service.IService;
import data_layer.implement.IngredientDAO;

public class IngredientService implements IService<Ingredient> {
    IngredientDAO ingredientDAO;

    /**
     * Constructs a new IngredientService object.
     */
    public IngredientService() {
        ingredientDAO = new IngredientDAO();
    }

    /**
     * Creates a new ingredient.
     * 
     * @param object the ingredient object to be created
     * @throws Exception if the ingredient already exists
     */
    @Override
    public void create(Ingredient object) throws Exception {
        // check if ingredient is already exist
        Ingredient ingredient = getById(object.getCode());
        // if exist, throw exception
        if (ingredient != null) {
            throw new Exception("Ingredient is already exist");
            // else, add to database
        } else {
            ingredientDAO.insert(object);
        }
    }

    /**
     * Retrieves an Ingredient object by its ID.
     *
     * @param id the ID of the Ingredient to retrieve
     * @return the Ingredient object with the specified ID, or null if not found
     */
    @Override
    public Ingredient getById(String id) {
        try {
            List<Ingredient> ingredients = findIngredientList();
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getCode().equalsIgnoreCase(id)) {
                    return ingredient;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Searches for ingredients based on the given criteria.
     * 
     * @param t          The ingredient object containing the search criteria.
     * @param properties The properties to search for in the ingredient object.
     * @return A list of ingredients that match the search criteria.
     * @throws UnsupportedOperationException if the method is not implemented.
     */
    @Override
    public List<Ingredient> search(Ingredient t, String properties) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public void update(Ingredient objectBeUpdated, Ingredient objectInformation) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Deletes an ingredient from the system.
     * 
     * @param object The ingredient to be deleted.
     * @throws Exception If the ingredient is not found in the system.
     */
    @Override
    public void delete(Ingredient object) throws Exception {
        Ingredient ingredientFoundByCode = getById(object.getCode());
        if (ingredientFoundByCode == null) {
            throw new Exception("Ingredient is not exist");
        } else {
            ingredientDAO.delete(ingredientFoundByCode);
        }
    }

    /**
     * Retrieves a list of ingredients.
     *
     * @return The list of ingredients.
     * @throws Exception
     */
    public List<Ingredient> findIngredientList() throws Exception {
        ingredientDAO.clear();
        ingredientDAO.loadDataFromFile();
        return ingredientDAO.getIngredientsList();
    }

}
