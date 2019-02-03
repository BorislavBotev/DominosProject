package userSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class IngredientsStorage {
	private static Map<Ingredient.IngredientsCategory,List<Ingredient>> storage=new HashMap<>();
//	public IngredientsStorage() {
//		storage.put(Ingredient.IngredientsCategory.VEGETABLE, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.MEAT, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.SAUCE, new HashSet<Ingredient>());
//		storage.put(Ingredient.IngredientsCategory.CHEESE, new HashSet<Ingredient>());
//	}
	public static List<Ingredient> listAllIngredientsWithIndexes() {
		List<Ingredient> ingredientsList=new ArrayList<>();
		int count=1;
		for(Entry<Ingredient.IngredientsCategory,List<Ingredient>> entry:storage.entrySet()) {
			System.out.println(entry.getKey());
			for(Ingredient p:entry.getValue()) {
				System.out.println(count++ +"- "+p);
				ingredientsList.add(p);
			}
		}
		return ingredientsList;
	}
	
	public static Map<Ingredient.IngredientsCategory, List<Ingredient>> getStorage() {
		return Collections.unmodifiableMap(storage);
	}
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
