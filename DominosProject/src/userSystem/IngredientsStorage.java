package userSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IngredientsStorage {
	private static Map<Ingredient.IngredientsCategory,Set<Ingredient>> storage=new HashMap<>();
//	public IngredientsStorage() {
//		storage.put(Ingredient.IngredientsCategory.VEGETABLE, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.MEAT, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.SAUCE, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.CHEESE, new HashSet<Ingredient>());
//	}
	
	public void addIngredient(Ingredient ingredient) {
		if(ingredient!=null) {
			this.storage.get(ingredient.getCategory()).add(ingredient);
		}
	}
	public void removeIngredient(Ingredient ingredient) {
		if(ingredient!=null) {
			this.storage.get(ingredient).remove(ingredient);
		}
	}
	
}
