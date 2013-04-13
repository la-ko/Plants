package de.lkornelsen.gardening.plants.server.facade.family.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.lkornelsen.gardening.plants.server.facade.family.dto.FamilyDto;
import de.lkornelsen.gardening.plants.server.plant.integration.bo.FamilyBo;

public class FamiliyServiceFacadeMapperUnitTest {

	private FamiliyServiceFacadeMapper out;

	@Before
	public void setUp() {
		out = new FamiliyServiceFacadeMapper();
	}

	@Test
	public void shouldMapBetweenFamilyDtoAndBo() {
		// Given
		final long id = 1000L;
		final long version = 1L;
		final String name = "Family's Name";

		FamilyDto familyDto = new FamilyDto();
		familyDto.setId(id);
		familyDto.setVersion(version);
		familyDto.setName(name);

		// When
		FamilyBo familyBo = out.map(familyDto, FamilyBo.class);

		// Then
		assertNotNull(familyBo);
		assertEquals(id, familyBo.getId());
		assertEquals(version, familyBo.getVersion());
		assertEquals(name, familyBo.getName());
	}
}
