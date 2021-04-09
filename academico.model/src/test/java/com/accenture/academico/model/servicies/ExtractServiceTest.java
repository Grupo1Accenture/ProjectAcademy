package com.accenture.academico.model.servicies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.academico.model.entities.Extract;
import com.accenture.academico.model.repositories.ExtractRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExtractServiceTest {

	@Autowired
	private ExtractService extractService;
	@Mock
	private ExtractRepository extractRepository;
	
	@Test
	void testFindAll() {
		List<Extract> extract = extractService.findAll();
		assertEquals(8, extract.size());
	}

	@Test
	void testFindById() {
		Extract extract = extractService.findById(1L);
		assertEquals(1, extract.getId());
		assertEquals(2, extract.getOperationStatus());
	}

}
