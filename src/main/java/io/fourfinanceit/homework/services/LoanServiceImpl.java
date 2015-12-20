package io.fourfinanceit.homework.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fourfinanceit.homework.models.Loan;
import io.fourfinanceit.homework.repositories.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Integer getDailyCountByIpAndClient(Date date, String ip, Long clientId) {
		return loanRepository.findAppCountByIp(date, ip, clientId);
	}

	@Override
	public void saveLoan(Loan loan) {
		loanRepository.save(loan);		
	}

}
