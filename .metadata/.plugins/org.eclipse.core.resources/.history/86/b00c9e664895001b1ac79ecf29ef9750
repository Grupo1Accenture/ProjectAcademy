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
public class Extrato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT-3")
	private Instant dataHoraMovimento;
	private Integer operationStatus;
	private Double valorOperacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private ContaCorrente conta;
	public Extrato() {
	}

	public Extrato(Long id, OperationStatus operationStatus, Double valorOperacao, ContaCorrente conta) {
		super();
		this.id = id;
		setDataHoraMovimento(getDataHoraMovimento());
		//setOperationStatus(operationStatus);
		this.valorOperacao = valorOperacao;
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataHoraMovimento() {
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
	public void setDataHoraMovimento(Instant dataHoraMovimento) {
		this.dataHoraMovimento = dataHoraMovimento;
	}
	
	public Double getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(Double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public ContaCorrente getConta() {
		return conta;
	}

	public void setConta(ContaCorrente conta) {
		this.conta = conta;
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
		Extrato other = (Extrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Extrato [id=" + id + ", dataHoraMovimento=" + dataHoraMovimento + ", operationStatus=" + operationStatus
				+ ", valorOperacao=" + valorOperacao + ", conta=" + conta + "]";
	}
	

}

