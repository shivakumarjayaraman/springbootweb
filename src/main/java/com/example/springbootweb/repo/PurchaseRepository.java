package com.example.springbootweb.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.springbootweb.model.Purchase;

@Repository
public class PurchaseRepository {
	private JdbcTemplate jdbc;
	
	public PurchaseRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public void persist(Purchase p) {
		String sql = "INSERT into purchase values (NULL, ?, ?)";
		jdbc.update(sql, p.getProduct(), p.getPrice());
	}
	
	public List<Purchase> findAllPurchases() {
		String sql = "SELECT * from purchase";
		
		RowMapper<Purchase> rm = (r, i) -> {
			Purchase row = new Purchase();
			row.setId(r.getInt("id"));
			row.setPrice(r.getBigDecimal("price"));
			row.setProduct(r.getString("product"));
			return row;
		};
		return jdbc.query(sql,  rm);
	}
}
