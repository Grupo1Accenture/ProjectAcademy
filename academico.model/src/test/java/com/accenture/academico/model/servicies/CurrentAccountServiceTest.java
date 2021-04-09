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
import com.accenture.academico.model.entities.Client;
import com.accenture.academico.model.entities.CurrentAccount;
import com.accenture.academico.model.repositories.CurrentAccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurrentAccountServiceTest {

	@Autowired
	private CurrentAccountService currentAccountService;
	@Mock
	private CurrentAccountRepository currentAccountRepository;
	
	@Test
	void testFindAll() {
		List<CurrentAccount> currentAccount = currentAccountService.findAll();
		assertEquals(3, currentAccount.size());
	}

	@Test
	void testFindById() {
		CurrentAccount currentAccount = currentAccountService.findById(1L);
		assertEquals(1, currentAccount.getId());
		assertEquals("5555-5", currentAccount.getCurrentAccountNumber());
	}

	@Test
	void testInsert() {
		Client client = new Client(null, "Claudemir", "12345678911","81987564235");
		Agency agency = new Agency(null, "Santander", "Rua teste","8112345678");
		CurrentAccount currentAccount = new CurrentAccount(null, "7894-5", 0.0,client, agency);
		Mockito.when(currentAccountRepository.save(currentAccount)).thenReturn(currentAccount);
		assertThat(currentAccountService.insert(currentAccount)).isEqualTo(currentAccount);
	}

}
