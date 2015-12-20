package io.fourfinanceit.homework.utils;

import java.util.Date;

import io.fourfinanceit.homework.enums.LoanStatusEnum;
import io.fourfinanceit.homework.models.Client;
import io.fourfinanceit.homework.models.Loan;
import io.fourfinanceit.homework.dtos.LoanDTO;

public class Mapper {
	public static Client mapClient(LoanDTO loanDTO){
		return new Client(loanDTO.getName(), loanDTO.getSurname());
	}
	
	public static Loan mapLoan(LoanDTO loanDTO, Client client, Date date, String ip){
		return new Loan(loanDTO.getAmount(), loanDTO.getTerm(), client, LoanStatusEnum.ACCEPTED.toString(), date, ip);
	}
}
