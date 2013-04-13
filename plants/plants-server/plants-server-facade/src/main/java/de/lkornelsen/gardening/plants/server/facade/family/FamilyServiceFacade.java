package de.lkornelsen.gardening.plants.server.facade.family;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ma.glasnost.orika.MapperFacade;

import org.apache.commons.lang3.Validate;
import org.springframework.transaction.annotation.Transactional;

import de.lkornelsen.gardening.plants.server.facade.family.dto.FamilyDto;
import de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo;
import de.lkornelsen.gardening.plants.server.plant.service.IFamilyService;

@Transactional
public class FamilyServiceFacade {
	@Inject
	private IFamilyService service;

	@Inject
	@Named("familyServiceFacadeMapper")
	private MapperFacade mapper;

	@Transactional(readOnly=true)
	public List<FamilyDto> retrieveAll() {
		Validate.notNull(service, "Service must be set.");
		Validate.notNull(mapper, "Mapper must be set.");

		List<FamilyBo> retrieveAll = service.retrieveAll();
		
		List<FamilyDto> retValue = mapper.mapAsList(retrieveAll, FamilyDto.class);

		return retValue;
	}
}
