package com.team1.betterhip.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.betterhip.command.BetterHipCommand;
import com.team1.betterhip.util.Constant;

@Controller
public class CakeListController {
	
	//Field
	BetterHipCommand cakeListViewCommand;
	JdbcTemplate template;
	
	//template, command AutoWired
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	@Autowired
	public void setCommand(BetterHipCommand cakeListViewCommand) {
		this.cakeListViewCommand = cakeListViewCommand;
	}
	
	//Method
	
	//케이크 리스트 출력
	@RequestMapping("/cakeList")
	public String cakeList(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		cakeListViewCommand.execute(request,model);
		return "order/cakeList";
	}
	
	
	
	
	
	
	
	
}
