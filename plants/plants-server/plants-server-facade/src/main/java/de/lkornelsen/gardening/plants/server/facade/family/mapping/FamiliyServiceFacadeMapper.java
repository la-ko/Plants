package de.lkornelsen.gardening.plants.server.facade.family.mapping;

import javax.inject.Named;

import de.lkornelsen.gardening.plants.server.facade.family.dto.FamilyDto;
import de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Named("familyServiceFacadeMapper")
public class FamiliyServiceFacadeMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		factory.registerClassMap(factory
				.classMap(FamilyBo.class, FamilyDto.class).byDefault()
				.toClassMap());
	}
}
