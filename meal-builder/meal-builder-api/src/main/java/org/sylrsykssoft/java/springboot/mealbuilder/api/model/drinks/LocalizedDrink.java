/**
 * LocalizedDrink.java 15 mar. 2021
 *
 */
package org.sylrsykssoft.java.springboot.mealbuilder.api.model.drinks;

import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.DrinkApiConstants.DRINK_ID_JOIN_COLUMN;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.DrinkApiConstants.REPOSITORY_LOCALIZED_DRINK_ENTITY_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.DrinkApiConstants.REPOSITORY_LOCALIZED_DRINK_TABLE_NAME;
import static org.sylrsykssoft.java.springboot.mealbuilder.api.configuration.drinks.DrinksApiConstants.DRINK_SCHEMA_NAME;
import static org.sylrsykssoft.springboot.common.api.configuration.CommonAPIConstants.BASE_LOCALIZED_MODEL_LOCALE_COLUMN;
import static org.sylrsykssoft.springboot.common.api.configuration.CommonAPIConstants.BASE_LOCALIZED_MODEL_NAME_FIELD_NAME_COLUMN;
import static org.sylrsykssoft.springboot.common.api.configuration.CommonAPIConstants.BASE_LOCALIZED_MODEL_NAME_VALUE_COLUMN;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.sylrsykssoft.springboot.common.api.model.embeddable.BaseEmbeddedLocalizedValueModel;
import org.sylrsykssoft.springboot.common.api.model.embeddable.LocalizedFieldNameModel;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@Table(name = REPOSITORY_LOCALIZED_DRINK_TABLE_NAME, schema = DRINK_SCHEMA_NAME, catalog = DRINK_SCHEMA_NAME, uniqueConstraints = {
		@UniqueConstraint(columnNames = { BASE_LOCALIZED_MODEL_LOCALE_COLUMN,
				BASE_LOCALIZED_MODEL_NAME_FIELD_NAME_COLUMN, BASE_LOCALIZED_MODEL_NAME_VALUE_COLUMN }) })
@Entity(name = REPOSITORY_LOCALIZED_DRINK_ENTITY_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, includeFieldNames = true)
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class LocalizedDrink extends BaseEmbeddedLocalizedValueModel<LocalizedFieldNameModel> {

	@ManyToOne
	@JoinColumn(name = DRINK_ID_JOIN_COLUMN, nullable = false)
	Drink drink;

}
