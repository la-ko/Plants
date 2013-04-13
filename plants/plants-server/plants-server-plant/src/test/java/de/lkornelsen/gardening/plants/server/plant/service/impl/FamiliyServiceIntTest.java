package de.lkornelsen.gardening.plants.server.plant.service.impl;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.Validate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.lkornelsen.gardening.plants.server.common.ServerConfig;
import de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo;
import de.lkornelsen.gardening.plants.server.plant.service.IFamilyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServerConfig.class }, loader = AnnotationConfigContextLoader.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class FamiliyServiceIntTest {
	@Inject
	private IFamilyService out;

	@PersistenceContext
	EntityManager entityManager;

	@Test
	public void shouldRetrieveAllFamilies() {
		Validate.notNull(out);

		// Given

		// When
		out.retrieveAll();
	}

	@Test
	public void shouldInsertNewFamily() {
		// Given
		FamilyBo family = new FamilyBo();
		family.setName("My Family");

		// When
		long id = out.createOrUpdate(family);
		entityManager.flush();

		// Then
		FamilyBo retrievedFamily = out.retrieve(id);

		assertTrue("Returned ID must be greater than 0.", id > 0);
		assertTrue("Version after an insert must be 0.",
				retrievedFamily.getVersion() == 0);
	}

	@Test
	public void shouldUpdateExistingFamily() {
		// Given
		FamilyBo family = new FamilyBo();
		family.setName("My Family");
		long id = out.createOrUpdate(family);

		FamilyBo updatedFamily = new FamilyBo();
		updatedFamily.setId(id);
		updatedFamily.setName("My Family Updated");

		entityManager.flush();

		// When
		long updatedId = out.createOrUpdate(updatedFamily);
		entityManager.flush();

		// Then
		FamilyBo retrievedFamily = out.retrieve(id);

		assertTrue("ID after an update must be the same.", id == updatedId);
		assertTrue("Version after the update must be 1.",
				retrievedFamily.getVersion() == 1);
	}
}
