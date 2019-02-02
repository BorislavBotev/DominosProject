package userSystem;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import exceptions.InvalidIngredientException;

public class IngredientsStorage {
	private static Map<Ingredient.IngredientsCategory,Set<Ingredient>> storage=new TreeMap<>();
	
	public static void addIngredient(Ingredient ingredient) throws InvalidIngredientException {
		if(ingredient!=null) {
			Set<Ingredient> ingredients = new HashSet<Ingredient>();
			if(storage.containsKey(ingredient.getCategory())) {
				ingredients = storage.get(ingredient.getCategory());
			} 
			ingredients.add(ingredient);
			storage.put(ingredient.getCategory(), ingredients);
		} else {
			throw new InvalidIngredientException("Invalid Ingredient!");
		}
	}
	
	public static void removeIngredient(Ingredient ingredient) {
		if(ingredient!=null && storage.get(ingredient.getCategory()).contains(ingredient)) {
			storage.remove(ingredient.getCategory(), ingredient);
		}
	}
	
}
