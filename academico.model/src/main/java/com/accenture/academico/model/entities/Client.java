package com.accenture.academico.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.caelum.stella.validation.CPFValidator;

@Entity
@Table(name = "tb_cliente")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	
	@NotNull
	@Column(unique=true, length=14)
	private String cpf;
	
	@NotNull
	private String phone;
	
	
	/*@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Agencia> agencia = new ArrayList<>();*/
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<CurrentAccount> account = new ArrayList<>();
	
	public Client() {
		
	}

	public Client(Long id, String name, String cpf, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		boolean valido = valida(cpf);
		if(valido == true) {
			this.cpf = cpf;
		}
		//this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<CurrentAccount> getAccount() {
		return account;
	}

	public void setAccount(List<CurrentAccount> account) {
		this.account = account;
	}
	public static boolean valida(String cpf) { 
	    CPFValidator cpfValidator = new CPFValidator(); 
	    try{ cpfValidator.assertValid(cpf); 
	    return true; 
	    }catch(Exception e){ 
	        e.printStackTrace(); 
	        return false; 
	        } 
	    }

	@Override
	public String toString() {
		return "Client [account=" + account + "]";
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
