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
public class SignupController {
	
	//Field
	private BetterHipCommand signupCommand, signupCheckIdCommand;
	private JdbcTemplate template;
	
	//command, template AutoWired
	@Autowired
	public void setCommand(BetterHipCommand signupCommand, BetterHipCommand signupCheckIdCommand) {
		this.signupCommand = signupCommand;
		this.signupCheckIdCommand = signupCheckIdCommand;
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	//Method
	
	//회원가입 초기 화면으로 이동
	@RequestMapping("/signup")
	public String signup() {
		return "signup/signupForm";
	}
	
	//아이디 중복확인
	@RequestMapping("/signupCheckId")
	public String signupCheckId(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		signupCheckIdCommand.execute(request, model);
		
		return "signup/signupCheckIdResult";
	}
	
	//회원가입 정보 입력
	@RequestMapping("/signupSubmit")
	public String signupSubmit(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		signupCommand.execute(request, model);
		return "signup/signupResult";
	}
	
	
	
	
	
}
