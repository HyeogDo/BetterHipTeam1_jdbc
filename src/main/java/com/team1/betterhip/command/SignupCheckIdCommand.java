package com.team1.betterhip.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.team1.betterhip.dao.SignupDao;

public class SignupCheckIdCommand implements BetterHipCommand {
	
	


	@Override
	public void execute(HttpServletRequest request,Model model) {
		// TODO Auto-generated method stub
		
		//Request 선언
		Map<String, Object> map = model.asMap();
		request= (HttpServletRequest) map.get("request");
		
		//Dao 선언
		SignupDao dao = new SignupDao();
		
		//변수 선언
		String user_id = request.getParameter("user_id");
		
		//Dao 함수로 DB에 중복 확인 후 결과값 받아오기
		int result = dao.signupCheckId(user_id);
		
		//나온 결과값 session에 저장
		if (result == 1) {
			model.addAttribute("result", "이미 사용중인 아이디 입니다.");
		}else {
			model.addAttribute("result", "사용 가능한 아이디 입니다.");
		}
	}

}
