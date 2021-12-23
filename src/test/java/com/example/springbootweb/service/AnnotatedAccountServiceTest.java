package com.example.springbootweb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.springbootweb.model.Account;
import com.example.springbootweb.repo.AccountRepository;

@ExtendWith(MockitoExtension.class)
public class AnnotatedAccountServiceTest {
	@Mock
	AccountRepository ar;
	
	@InjectMocks
	AccountService as;
	
	@Test
	public void testTransfer() {
		when(ar.findAccountsByName("sender")).thenReturn(List.of(makeAccount(1, 2000, "sender")));
		when(ar.findAccountsByName("receiver")).thenReturn(List.of(makeAccount(2, 2000, "receiver")));
		
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
