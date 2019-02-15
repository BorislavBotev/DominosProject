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


	private static IngredientsStorage ingredientsStorage = null;
	private Map<Ingredient.IngredientsCategory,Set<Ingredient>> storage;
	
	private IngredientsStorage() {
		this.storage = new TreeMap<>();
	}
	
	public static IngredientsStorage getIngredientsStorage() {
		if(IngredientsStorage.ingredientsStorage == null) {
			ingredientsStorage = new IngredientsStorage();
		} 
		return ingredientsStorage;
	}
	
	public List<Ingredient> listAllIngredientsWithIndexes() {
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
	
	public Map<Ingredient.IngredientsCategory, Set<Ingredient>> getStorage() {
		return Collections.unmodifiableMap(this.storage);
	}
	
	
	public void addIngredient(Ingredient ingredient) throws InvalidIngredientException {
		if(ingredient!=null) {
			Set<Ingredient> ingredients = new HashSet<Ingredient>();
			if(this.storage.containsKey(ingredient.getCategory())) {
				ingredients = this.storage.get(ingredient.getCategory());
			} 
			ingredients.add(ingredient);
			this.storage.put(ingredient.getCategory(), ingredients);
		} else {
			throw new InvalidIngredientException("Invalid Ingredient!");
		}
	}
	
	public void removeIngredient(Ingredient ingredient) {
		if(ingredient!=null && this.storage.get(ingredient.getCategory()).contains(ingredient)) {
			this.storage.remove(ingredient.getCategory(), ingredient);
		}
	}
	
}
