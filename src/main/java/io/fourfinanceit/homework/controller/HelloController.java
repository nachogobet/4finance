package io.fourfinanceit.homework.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.fourfinanceit.homework.dtos.LoanDTO;
import io.fourfinanceit.homework.enums.LoanStatusEnum;
import io.fourfinanceit.homework.models.Client;
import io.fourfinanceit.homework.models.Loan;
import io.fourfinanceit.homework.services.ClientService;
import io.fourfinanceit.homework.services.LoanService;
import io.fourfinanceit.homework.utils.Mapper;

@Controller
public class HelloController {
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/getloan", method = GET)
	public String getLoan(Model model) {
		model.addAttribute("loanDTO", new LoanDTO());
		return "form";
	}

	@RequestMapping(value="/submitloan", method=RequestMethod.POST)
	public String submitLoan(@ModelAttribute("loanDTO") LoanDTO loanDTO, HttpServletRequest request, Model model) {
		Client client = clientService.findClient(loanDTO.getName(), loanDTO.getSurname());
		
		if(client == null){
			client = Mapper.mapClient(loanDTO);
			clientService.saveClient(client);
		}
		
		Loan loan = Mapper.mapLoan(loanDTO, client, new Date(), request.getRemoteAddr());
		
		if(isTooRisky(loan)){
			loan.setStatus(LoanStatusEnum.REJECTED.toString());
			client.getLoans().add(loan);
			clientService.saveClient(client);
			loanService.saveLoan(loan);
			return "rejected";
		}
		
		client.getLoans().add(loan);
		clientService.saveClient(client);
		loanService.saveLoan(loan);
		model.addAttribute("loanId", loan.getId());
		model.addAttribute("amount", loan.getAmount());
		model.addAttribute("term", loan.getTerm());
		return "accepted";
	}

	private boolean isTooRisky(Loan loan) {
		BigDecimal amount = loan.getAmount();
		String ip = loan.getIp();
		Client client = loan.getClient();
		
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		BigDecimal maxAmount = new BigDecimal(Long.valueOf(prop.getProperty("maxamount")).toString());
		Integer maxTime = Integer.valueOf(prop.getProperty("maxtime")).intValue();
		Integer minTime = Integer.valueOf(prop.getProperty("mintime")).intValue();
		Integer maxApps = Integer.valueOf(prop.getProperty("maxappsperday")).intValue();
		
		Date now = new Date();
		
		if((amount.compareTo(maxAmount)== 0 || amount.compareTo(maxAmount)== 1) && now.getHours()>=minTime && now.getHours()<=maxTime)
			return true;
		
		if(loanService.getDailyCountByIpAndClient(now, ip, client.getId())>=maxApps)
			return true;
		
		return false;
	}
}
