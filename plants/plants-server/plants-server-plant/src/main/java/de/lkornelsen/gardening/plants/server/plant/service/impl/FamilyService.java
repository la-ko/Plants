package de.lkornelsen.gardening.plants.server.plant.service.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang3.Validate;

import de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo;
import de.lkornelsen.gardening.plants.server.plant.service.IFamilyService;

@Named
public class FamilyService implements IFamilyService {

	@PersistenceContext
	EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.lkornelsen.gardening.plants.server.plant.service.impl.IFamilyService
	 * #retrieveAll()
	 */
	@Override
	public List<FamilyBo> retrieveAll() {
		validatePreConditions();

		CriteriaQuery<FamilyBo> query = entityManager.getCriteriaBuilder()
				.createQuery(FamilyBo.class);
		query.from(FamilyBo.class);

		return entityManager.createQuery(query).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.lkornelsen.gardening.plants.server.plant.service.impl.IFamilyService
	 * #retrieve(long)
	 */
	@Override
	public FamilyBo retrieve(long id) {
		Validate.notNull(entityManager);

		return entityManager.find(FamilyBo.class, Long.valueOf(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.lkornelsen.gardening.plants.server.plant.service.impl.IFamilyService
	 * #createOrUpdate
	 * (de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo)
	 */
	@Override
	public long createOrUpdate(FamilyBo family) {
		family = entityManager.merge(family);

		return family.getId();
	}

	private void validatePreConditions() {
		Validate.notNull(entityManager);
	}
}
