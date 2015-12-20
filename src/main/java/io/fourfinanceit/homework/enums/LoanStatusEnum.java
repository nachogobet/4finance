package io.fourfinanceit.homework.enums;

public enum LoanStatusEnum {
	ACCEPTED("A"), REJECTED("R");

	private String statusCode;

	private LoanStatusEnum(String s) {
		statusCode = s;
	}

	public String getStatusCode() {
		return statusCode;
	}
}
