/**
 * Cocktail.java 27 feb. 2021
 *
 */
package org.sylrsykssoft.java.springboot.mealbuilder.api.model.drinks;

import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.CocktailApiConstants.GLASS_COCKTAIL_ID_JOIN_COLUMN;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.CocktailApiConstants.REPOSITORY_COCKTAIL_ENTITY_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.CocktailApiConstants.REPOSITORY_COCKTAIL_TABLE_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.DrinksApiConstants.DRINK_SCHEMA_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.ingredients.IngredientApiConstants.REPOSITORY_INGREDIENT_TABLE_NAME;
import static org.sylrsykssoft.springboot.common.api.configuration.CommonAPIConstants.BASE_MODEL_NAME_ID_COLUMN;
import static org.sylrsykssoft.springboot.common.api.configuration.CommonAPIConstants.BASE_NAME_MODEL_NAME_NAME_COLUMN;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.CocktailApiConstants.CocktailClassification;
import org.sylrsykssoft.java.springboot.mealbuilder.api.model.embeddable.PreparationData;
import org.sylrsykssoft.java.springboot.mealbuilder.api.model.embeddable.PriceData;
import org.sylrsykssoft.springboot.common.api.model.BaseNameModel;
import org.sylrsykssoft.springboot.common.api.model.embeddable.AuditModel;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * Cocktail
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Table(name = REPOSITORY_COCKTAIL_TABLE_NAME, schema = DRINK_SCHEMA_NAME, catalog = DRINK_SCHEMA_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = { BASE_NAME_MODEL_NAME_NAME_COLUMN }) })
@Entity(name = REPOSITORY_COCKTAIL_ENTITY_NAME)
@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
public class Cocktail extends BaseNameModel<Long> {

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Type field is mandatory")
	@Builder.Default
	CocktailClassification type = CocktailClassification.APPETIZER;

	@Embedded
	@NotNull(message = "Preparation field is mandatory")
	PreparationData preparation;

	@Embedded
	@NotNull(message = "Price field is mandatory")
	PriceData price;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = GLASS_COCKTAIL_ID_JOIN_COLUMN, referencedColumnName = BASE_MODEL_NAME_ID_COLUMN)
	GlassCocktail glassCocktail;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = REPOSITORY_INGREDIENT_TABLE_NAME, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<CocktailIngredient> cocktailIngredients;
	
	@Embedded
	@NotNull(message = "CocktailCreationData field is mandatory")
	AuditModel cocktailCreationData;
	
}
