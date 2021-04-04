package com.accenture.academico.model.entities;

public class Testando {

	public static void main(String[] args) {
		
		ContaCorrente c1 = new ContaCorrente();
		
		Extrato e1 = new Extrato();
		
		c1.setContaCorrenteNumero("000555-");
		c1.setId(2L);
		c1.setSaldo(250.00);
		e1.setId(1L);
		e1.setValorOperacao(500.00);
		e1.setConta(c1);
		
		
		System.out.println(e1.getConta().getId());
		

	}

}
