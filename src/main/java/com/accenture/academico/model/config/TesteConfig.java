package com.accenture.academico.model.config;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.accenture.academico.model.entities.Cliente;
import com.accenture.academico.model.repositories.ClienteRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente1 = new Cliente(null, "Claudemir", "888.888.888-88", "988888888");
		Cliente cliente2 = new Cliente(null, "Adson", "777.777.777-77", "977777777"); 
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
	}
}
