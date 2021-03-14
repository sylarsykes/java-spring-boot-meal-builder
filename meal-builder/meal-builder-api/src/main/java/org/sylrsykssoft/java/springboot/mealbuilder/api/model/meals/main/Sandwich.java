/**
 * Salad.java 28 feb. 2021
 *
 */
package org.sylrsykssoft.java.springboot.mealbuilder.api.model.meals.main;

import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.ApiConstants.FOOD_SIZE_SIZE_COLUMN;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.ingredients.IngredientApiConstants.REPOSITORY_INGREDIENT_TABLE_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.MealsApiConstants.MEAL_SCHEMA_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.MealsApiConstants.MEAL_TYPE_TYPE_COLUMN;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.MealsApiConstants.REPOSITORY_CATEGORY_TABLE_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.REPOSITORY_SANDWICH_ENTITY_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.REPOSITORY_SANDWICH_TABLE_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.SANDWICH_BREAD_MAX_LENGTH;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.SANDWICH_BREAD_MIN_LENGTH;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.SANDWICH_CATEGORY_MAX_LENGTH;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.SANDWICH_CATEGORY_MIN_LENGTH;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.SANDWICH_INGREDIENT_MAX_LENGTH;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.meals.SandwichApiConstants.SANDWICH_INGREDIENT_MIN_LENGTH;
import static org.sylrsykssoft.springboot.common.api.configuration.CommonAPIConstants.BASE_NAME_MODEL_NAME_NAME_COLUMN;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.sylrsykssoft.java.springboot.mealbuilder.api.model.embeddable.FoodSizeData;
import org.sylrsykssoft.java.springboot.mealbuilder.api.model.embeddable.PreparationData;
import org.sylrsykssoft.java.springboot.mealbuilder.api.model.embeddable.PriceData;
import org.sylrsykssoft.java.springboot.mealbuilder.api.model.meals.embeddable.MealTypeData;
import org.sylrsykssoft.springboot.common.api.model.BaseNameModel;
import org.sylrsykssoft.springboot.common.api.model.embeddable.AuditModel;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * Sandwich
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Table(name = REPOSITORY_SANDWICH_TABLE_NAME, schema = MEAL_SCHEMA_NAME, catalog = MEAL_SCHEMA_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = { MEAL_TYPE_TYPE_COLUMN, FOOD_SIZE_SIZE_COLUMN, BASE_NAME_MODEL_NAME_NAME_COLUMN }) })
@Entity(name = REPOSITORY_SANDWICH_ENTITY_NAME)
@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class Sandwich extends BaseNameModel<Long> {

	@Embedded
	@NotNull(message = "Type field is mandatory")
	MealTypeData type;
	
	@Embedded
	@NotNull(message = "Preparation field is mandatory")
	PreparationData preparation;
	
	@Embedded
	@NotNull(message = "Size field is mandatory")
	FoodSizeData size;
	
	@Embedded
	@NotNull(message = "Price field is mandatory")
	PriceData price;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = REPOSITORY_CATEGORY_TABLE_NAME, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Size(min = SANDWICH_CATEGORY_MIN_LENGTH, max = SANDWICH_CATEGORY_MAX_LENGTH)
	Set<SandwichCategory> sandwichCategories;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = REPOSITORY_SANDWICH_TABLE_NAME, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Size(min = SANDWICH_BREAD_MIN_LENGTH, max = SANDWICH_BREAD_MAX_LENGTH)
	List<SandwichBread> sandwichBreads;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = REPOSITORY_INGREDIENT_TABLE_NAME, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Size(min = SANDWICH_INGREDIENT_MIN_LENGTH, max = SANDWICH_INGREDIENT_MAX_LENGTH)
	Set<SandwichIngredient> sandwichIngredients;
	
	@Embedded
	@NotNull(message = "SandwichCreationData field is mandatory")
	AuditModel sandwichCreationData;
	
}
