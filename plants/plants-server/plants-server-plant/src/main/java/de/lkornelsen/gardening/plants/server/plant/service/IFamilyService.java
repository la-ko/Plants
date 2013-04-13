package de.lkornelsen.gardening.plants.server.plant.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo;

public interface IFamilyService {

	@Transactional(readOnly = true)
	public abstract List<FamilyBo> retrieveAll();

	public abstract FamilyBo retrieve(long id);

	public abstract long createOrUpdate(FamilyBo family);

}