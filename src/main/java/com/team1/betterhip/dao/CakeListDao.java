package com.team1.betterhip.dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.team1.betterhip.dto.CakeListDto;
import com.team1.betterhip.util.Constant;

public class CakeListDao {
	
	//Field
	JdbcTemplate template;
	
	//Constructor
	public CakeListDao() {
		// TODO Auto-generated constructor stub
		this.template = Constant.template;
	}
	
	//Method
	
	//케이크 리스트 뷰 출력
	public ArrayList<CakeListDto> list(int start, int len, String order, String search ) {
		ArrayList<CakeListDto> dtos = null;
		String query = "select cake_id, cake_name, cake_saleprice, cake_status, cake_img"
					+ " from cake "
					+ search
					+ " order by " + order 
					+ " limit " + start + ", " + len;
		System.out.println(query);
		
		dtos = (ArrayList<CakeListDto>) template.query(query, new BeanPropertyRowMapper<CakeListDto>(CakeListDto.class));
		return dtos;
	}
	
	public int getTotalRows(String search) {
		String query = "select count(cake_id) from cake " + search;
		return template.queryForObject(query, Integer.class);
	}
	
	
}
