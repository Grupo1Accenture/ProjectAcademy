package com.accenture.academico.model.entities.enums;

public enum OperationStatus {
	SAQUE(1),
	DEPOSITO(2),
	TRANSFERENCIA(3);
	private int code;
	
	private OperationStatus(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	
	public static OperationStatus valueOf(int code) {
		for(OperationStatus value : OperationStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo invalido");
	}
}
