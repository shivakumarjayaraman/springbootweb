package com.example.springbootweb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import com.example.springbootweb.model.Account;
import com.example.springbootweb.repo.AccountRepository;


public class AccountServiceTest {
	AccountRepository ar;
	AccountService as;

	@BeforeEach
	public void setUp() {
		ar = mock(AccountRepository.class);
		when(ar.findAccountsByName("yo")).thenReturn(List.of(makeAccount(20, 2000, "yo")));
		when(ar.findAccountsByName("sender")).thenReturn(List.of(makeAccount(1, 2000, "sender")));
		when(ar.findAccountsByName("receiver")).thenReturn(List.of(makeAccount(2, 2000, "receiver")));

		as  = new AccountService(ar);
	}

	@Test
	public void accountMockExample() {
		List<Account> all = as.getAll("yo");

		assertEquals(1, all.size());
		assertEquals("yo", all.get(0).getName());
	}

	@Test
	@DisplayName("Test the amount is transferred " +
			"from one account to another if no exception occurs.")
	public void happyTransfer() {
		as.transfer("sender", "receiver", 100, false);
		
		
		//System.out.println(Mockito.mockingDetails(ar).printInvocations());
		Collection<Invocation> invs = Mockito.mockingDetails(ar).getInvocations();
		
		Invocation ca1 = (Invocation) invs.toArray()[2];
		Invocation ca2 = (Invocation) invs.toArray()[3];
		Object[] args = ca1.getRawArguments();
		assertEquals(BigDecimal.valueOf(1900.0), args[1]);
		
		args = ca2.getRawArguments();
		assertEquals(BigDecimal.valueOf(2100.0), args[1]);
	}

	public Account makeAccount(long id, double amount, String name) {
		Account a = new Account();
		a.setAmount(BigDecimal.valueOf(amount));
		a.setId(id);
		a.setName(name);

		return a;
	}
}
