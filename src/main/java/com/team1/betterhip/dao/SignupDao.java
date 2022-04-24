package com.team1.betterhip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.team1.betterhip.util.Constant;

public class SignupDao {
	
	//field
	JdbcTemplate template;
	
	//constructor
	public SignupDao() {
		// TODO Auto-generated constructor stub
		this.template = Constant.template;
	}
	
	//Method
	//아이디 찾기
	public int signupCheckId(String user_id) {
		String query = "select count(*) "
					+ "from user "
					+ "where user_id='" + user_id + "'";
		return template.queryForObject(query, Integer.class);
	}
	
	//회원가입
	public String signup(final String user_id, final int user_pw, final String user_name, 
			final String user_email, final String user_phone, final String user_postcode,
			final String user_address, final String user_address_detail, final boolean user_marketing) {
		String result = "failed"; 
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into user(user_id, user_pw, user_name, user_email, "
						+ "user_phone, user_postcode, user_address, user_address_detail, "
						+ "user_marketing, user_joindate)"
						+ " values(?,?,?,?,?,?,?,?,?,now())";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, user_id);
				preparedStatement.setInt(2, user_pw);
				preparedStatement.setString(3, user_name);
				preparedStatement.setString(4, user_email);
				preparedStatement.setString(5, user_phone);
				preparedStatement.setString(6, user_postcode);
				preparedStatement.setString(7, user_address);
				preparedStatement.setString(8, user_address_detail);
				preparedStatement.setBoolean(9, user_marketing);
				
				return preparedStatement;
			}
		});
		if (template != null) result = "complete";
		return result;
	}
	
}
