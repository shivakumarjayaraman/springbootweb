package com.example.springbootweb.service;

import java.math.BigDecimal;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springbootweb.model.Account;
import com.example.springbootweb.repo.AccountRepository;

@Service
public class AccountService {
	private AccountRepository acctRepo;

	public AccountService(AccountRepository acctRepo) {
		super();
		this.acctRepo = acctRepo;
	}
	
	public List<Account> getAll(String name) {
		return acctRepo.findAccountsByName(name);
	}
	
	@Transactional
	public void transfer(String from, String to, float amount, boolean fail) {
		 List<Account> srces = acctRepo.findAccountsByName(from);
		 if (srces == null || srces.size() == 0) throw new RuntimeException("Source not found " + from);
		 
		 List<Account> dsts = acctRepo.findAccountsByName(to);
		 if (dsts == null || dsts.size() == 0) throw new RuntimeException("Source not found " + from);
		 
		Account src = srces.get(0);
		Account dest = dsts.get(0);
		
		BigDecimal srcAmount = src.getAmount();
		BigDecimal destAmount = dest.getAmount();
		
		BigDecimal newSrcAmount = srcAmount.subtract(new BigDecimal(amount));
		
		if (newSrcAmount.floatValue() < 0) throw new RuntimeException("Not enough balance");
		
		acctRepo.changeAmount(src.getId(), newSrcAmount);
		acctRepo.changeAmount(dest.getId(), destAmount.add(new BigDecimal(amount)));
		
		if (fail) {
			throw new RuntimeException("Failing the transaction .. Should get rolled back");
		}
	}
}
