package userSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;
@XStreamAlias("ingredients")
public class Ingredients {
	private List<Ingredient> ingredientList=new ArrayList<Ingredient>();

	public List<Ingredient> getIngredients() {
		return ingredientList!=null?Collections.unmodifiableList(ingredientList):Collections.emptyList();
	}
	 
	
}
