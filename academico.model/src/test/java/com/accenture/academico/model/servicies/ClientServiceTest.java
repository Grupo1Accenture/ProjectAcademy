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

import com.accenture.academico.model.entities.Client;
import com.accenture.academico.model.repositories.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientServiceTest {

	@Autowired
	private ClientService clientService;
	@Mock
	private ClientRepository clientRepository;

	@Test
	void testFindAll() {
		List<Client> client = clientService.findAll();
		assertEquals(2, client.size());
	}
	
	@Test
	void testfindById() {
		Client client = clientService.findById(1L);
		assertEquals(1, client.getId());
		assertEquals("Diogo", client.getName());
	}
	
	@Test
	void insert() {
		Client client = new Client(null, "Claudemir", "12345678911","81987564235");
		Mockito.when(clientRepository.save(client)).thenReturn(client);
		assertThat(clientService.insert(client)).isEqualTo(client);
	}

}
