/**
 * LocalizedDrinkMappingContextConverter.java 21 mar. 2021
 *
 */
package org.sylrsykssoft.java.springboot.mealbuilder.api.mapper.converter.model.drinks;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.sylrsykssoft.java.springboot.mealbuilder.api.dto.drinks.LocalizedDrinkDTO;
import org.sylrsykssoft.java.springboot.mealbuilder.api.model.drinks.LocalizedDrink;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * LocalizedDrinkMappingContextConverter
 * 
 * @author juan.gonzalez.fernandez.jgf
 *
 */
@RequiredArgsConstructor
public final class LocalizedDrinkMappingContextConverter implements Converter<LocalizedDrinkDTO, LocalizedDrink> {

	@NonNull
	private LocalizedDrinkMapperConverter converter;
	
	/**
	 * {@inheritDoc}
	 */
	@Valid
	@Override
	public LocalizedDrink convert(@NotNull final MappingContext<LocalizedDrinkDTO, LocalizedDrink> context) {
		return converter.convert(context.getSource());
	}

}
