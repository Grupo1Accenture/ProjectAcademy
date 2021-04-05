package com.accenture.academico.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_conta_corrente")
public class ContaCorrente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String contaCorrenteNumero;
	private Double saldo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Cliente client;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "agencia_id")
	private Agencia agencia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Extrato> extrato = new ArrayList<>();
	
	public ContaCorrente() {
		
	}

	public ContaCorrente(Long id, String contaCorrenteNumero, Double saldo, Cliente client, Agencia agencia) {
		super();
		this.id = id;
		this.contaCorrenteNumero = contaCorrenteNumero;
		this.saldo = saldo;
		this.client = client;
		this.agencia = agencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContaCorrenteNumero() {
		return contaCorrenteNumero;
	}

	public void setContaCorrenteNumero(String contaCorrenteNumero) {
		this.contaCorrenteNumero = contaCorrenteNumero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}
	public void setSaque(Double saque) {
		double saldo = getSaldo() - saque;
		setSaldo(saldo);
	}
	public void setDeposito(Double deposito) {
		double saldo = this.saldo + deposito;
		setSaldo(saldo);
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	public List<Extrato> getExtrato() {
		return extrato;
	}

	public void setExtrato(List<Extrato> extrato) {
		this.extrato = extrato;
	}
	

	@Override
	public String toString() {
		return "ContaCorrente [id=" + id + ", contaCorrenteNumero=" + contaCorrenteNumero + ", saldo=" + saldo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
