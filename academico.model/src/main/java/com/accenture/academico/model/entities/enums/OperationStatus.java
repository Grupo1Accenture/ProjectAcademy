package com.accenture.academico.model.entities.enums;

public enum OperationStatus {
	WITHDRAW(1),
	INITIAL_DEPOSIT(2),
	DEPOSIT(3),
	ORIGIN_TRANSFER(4),
	DESTINATION_TRANSFER(5);
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
		throw new IllegalArgumentException("Invalid code");
	}
}
