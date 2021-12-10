package com.example.springbootweb.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springbootweb.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	List<Account> findAccountsByName(String name);
	
	@Query("select * from account where name = :name")
	List<Account> getEmByName(@Param("name") String name);
	
	
	@Modifying
	@Query("Update account set amount = :amount where id = :id")
	void changeAmount(@Param("id") long id, @Param("amount") BigDecimal amount);
}
