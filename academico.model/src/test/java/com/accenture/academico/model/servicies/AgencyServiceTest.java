package com.accenture.academico.model.servicies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.academico.model.entities.Agency;
import com.accenture.academico.model.repositories.AgencyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class AgencyServiceTest {

	@Autowired
	private AgencyService agencyService;
	@Mock
	private AgencyRepository agencyRepository;
	
	@Test
	void testFindAll() {
		List<Agency> agency = agencyService.findAll();
		assertEquals(1, agency.size());
	}

	@Test
	void testFindById() {
		Agency agency = agencyService.findById(1L);
		assertEquals(1, agency.getId());
		assertEquals("Itaú", agency.getName());
	}

	@Test
	void testInsert() {
		Agency agency = new Agency(null, "Santander", "Rua teste","8112345678");
		Mockito.when(agencyRepository.save(agency)).thenReturn(agency);
		assertThat(agencyService.insert(agency)).isEqualTo(agency);
	}

}
