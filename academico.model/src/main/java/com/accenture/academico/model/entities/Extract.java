package com.accenture.academico.model.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.accenture.academico.model.entities.enums.OperationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_extrato")
public class Extract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT-3")
	private Instant dateTimeMovement;
	private Integer operationStatus;
	private Double operationValue;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_id")
	private CurrentAccount account;
	public Extract() {
	}

	public Extract(Long id, OperationStatus operationStatus, Double operationValue, CurrentAccount account) {
		super();
		this.id = id;
		setDateTimeMovement(getDateTimeMovement());
		setOperationStatus(operationStatus);
		this.operationValue = operationValue;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDateTimeMovement() {
		return Instant.now();
	}

	public OperationStatus getOperationStatus() {
		return OperationStatus.valueOf(operationStatus);
	}
	public void setOperationStatus(OperationStatus operationStatus) {
		if(operationStatus != null) {
			this.operationStatus = operationStatus.getCode();
		}
	}
	public void setDateTimeMovement(Instant dateTimeMovement) {
		this.dateTimeMovement = dateTimeMovement;
	}
	
	public Double getOperationValue() {
		return operationValue;
	}

	public void setOperationValue(Double operationValue) {
		this.operationValue = operationValue;
	}

	public CurrentAccount getAccount() {
		return account;
	}

	public void setAccount(CurrentAccount account) {
		this.account = account;
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
		Extract other = (Extract) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Extract [id=" + id + ", dateTimeMovement=" + dateTimeMovement + ", operationStatus=" + operationStatus
				+ ", operationValue=" + operationValue + ", account=" + account + "]";
	}
	

}

