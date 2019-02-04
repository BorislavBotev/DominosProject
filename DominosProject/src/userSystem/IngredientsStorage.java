package userSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import exceptions.InvalidIngredientException;
import java.util.TreeMap;



public class IngredientsStorage {
	private static Map<Ingredient.IngredientsCategory,Set<Ingredient>> storage=new TreeMap<>();
//	public IngredientsStorage() {
//		storage.put(Ingredient.IngredientsCategory.VEGETABLE, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.MEAT, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.SAUCE, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.CHEESE, new HashSet<Ingredient>());
//	}
	public static List<Ingredient> listAllIngredientsWithIndexes() {
		List<Ingredient> ingredientsList=new ArrayList<>();
		int count=1;
		for(Entry<Ingredient.IngredientsCategory,Set<Ingredient>> entry:storage.entrySet()) {
			System.out.println(entry.getKey());
			for(Ingredient p:entry.getValue()) {
				System.out.println(count++ +"- "+p);
				ingredientsList.add(p);
			}
		}
		return ingredientsList;
	}
	
	public static Map<Ingredient.IngredientsCategory, Set<Ingredient>> getStorage() {
		return Collections.unmodifiableMap(storage);
	}
	


//public class IngredientsStorage {
	//private static Map<Ingredient.IngredientsCategory,Set<Ingredient>> storage=new TreeMap<>();
	
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
