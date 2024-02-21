package business_layer.service.implement;

import java.util.List;

import business_layer.entity.Drink;
import business_layer.service.IService;
import data_layer.implement.DrinkDAO;

public class DrinkService implements IService<Drink>{
    DrinkDAO drinkDAO;

    public DrinkService() {
        drinkDAO = new DrinkDAO();
    }

    @Override
    public void create(Drink object) throws Exception {
        //check if drink is already exist
        Drink drink = getById(object.getCode());
        //if exist, throw exception
        if (drink != null) {
            throw new Exception("Drink is already exist");
            //else, add to database
        } else {
            drinkDAO.insert(object);
        }
    }

    /**
     * Retrieves a Drink object by its code.
     *
     * @param code the code of the Drink to retrieve
     * @return the Drink object with the specified code, or null if not found
     */
    @Override
    public Drink getById(String code) {
        //get drink by code
        try {
            List<Drink> listDrinks = findDrinksList();
            for (Drink drink : listDrinks) {
                if (drink.getCode().equalsIgnoreCase(code)) {
                    return drink;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    
    /**
     * Retrieves a list of drinks from the data access object.
     * 
     * @return A list of Drink objects.
     * @throws Exception if an error occurs while retrieving the drinks.
     */
    public List<Drink> findDrinksList() throws Exception {
        drinkDAO.clear();
        drinkDAO.loadDataFromFile();
        return drinkDAO.getDrinkList();
    }

    @Override
    public List<Drink> search(Drink t, String properties) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    /**
     * Deletes a Drink object from the database.
     * 
     * @param object The Drink object to be deleted.
     * @throws Exception If the Drink object does not exist.
     */
    @Override
    public void delete(Drink object) throws Exception {
        Drink drinkFoundByCode = getById(object.getCode());
        if (drinkFoundByCode == null) {
            throw new Exception("Drink is not exist");
        } else {
            drinkDAO.delete(drinkFoundByCode);
        }
    }

    public void update(Drink drinkFoundByCode, String oldCode) throws Exception {
        // check is duplicate code
        boolean isDuplicateCode = false;
        List<Drink> listDrinks = findDrinksList();
        for (Drink drink : listDrinks) {
            if (drink.getCode().equalsIgnoreCase(drinkFoundByCode.getCode())
                    && !drink.getCode().equalsIgnoreCase(oldCode)) {
                isDuplicateCode = true;
                break;
            }
        }
        // if duplicate code, throw exception
        if (isDuplicateCode) {
            throw new Exception("The code is already exist");
        } else {
            Drink drinkBeUpdated = getById(oldCode);
            // update ingredient
            // set new information for ingredient
            drinkBeUpdated.setCode(drinkFoundByCode.getCode());
            drinkBeUpdated.setName(drinkFoundByCode.getName());
            drinkBeUpdated.setListIngredients(drinkFoundByCode.getListIngredients());
            drinkBeUpdated.setPrice(drinkFoundByCode.getPrice());
            // write to file
            drinkDAO.writeToFile();
        }
    }

    
}
