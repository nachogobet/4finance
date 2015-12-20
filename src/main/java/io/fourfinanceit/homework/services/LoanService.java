package io.fourfinanceit.homework.services;

import java.util.Date;

import io.fourfinanceit.homework.models.Loan;

public interface LoanService {
	Integer getDailyCountByIpAndClient(Date date, String ip, Long clientId);
	void saveLoan(Loan loan);
}
