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
public class CurrentAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String currentAccountNumber;
	private Double balance;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "agency_id")
	private Agency agency;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Extract> extract = new ArrayList<>();
	
	public CurrentAccount() {
		
	}

	public CurrentAccount(Long id, String currentAccountNumber, Double balance, Client client, Agency agency) {
		super();
		this.id = id;
		this.currentAccountNumber = currentAccountNumber;
		setInitialDeposit(balance);
		this.client = client;
		this.agency = agency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrentAccountNumber() {
		return currentAccountNumber;
	}

	public void setCurrentAccountNumber(String currentAccountNumber) {
		this.currentAccountNumber = currentAccountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double value) {
		this.balance = value;
	}
	public void setInitialDeposit(Double value) {
		this.balance += value;
	}
	public void setDeposit(Double deposit) {
		this.balance += deposit;
	}
	public void setWithdraw(Double withdraw) {
		this.balance -= withdraw;
	}
	public void setDestinationTransfer(Double transfer) {
		this.balance += transfer;
	}
	public void setOriginTransfer(Double transfer) {
		this.balance -= transfer;
	}
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
	public List<Extract> getExtract() {
		return extract;
	}

	public void setExtract(List<Extract> extract) {
		this.extract = extract;
	}
	

	@Override
	public String toString() {
		return "CurrentAccount [id=" + id + ", currentAccountNumber=" + currentAccountNumber + ", balance=" + balance + "]";
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
		CurrentAccount other = (CurrentAccount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
