package com.team1.betterhip.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.team1.betterhip.dao.SignupDao;

public class SignupCommand implements BetterHipCommand {


	@Override
	public void execute(HttpServletRequest request,Model model) {
		// TODO Auto-generated method stub
		
		//Request 선언
		Map<String, Object> map = model.asMap();
		request = (HttpServletRequest) map.get("request");
		
		//Dao 선언
		SignupDao dao = new SignupDao();
		
		//변수 선언
		String user_id = request.getParameter("user_id");
		int user_pw = Integer.parseInt(request.getParameter("user_pw"));
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		String user_phone = request.getParameter("user_phone");
		String user_postcode = request.getParameter("postcode");
		String user_address = request.getParameter("address1");
		String user_address_detail = request.getParameter("address2");
		//마케팅 수신 동의 여부 boolean으로 변경하기
		String[] checkbox = request.getParameterValues("check");
		boolean user_marketing = true;
		if (checkbox.length == 2) {
			user_marketing = false;
		}
		
		//Dao 함수로 DB에 정보 입력 후 성공 여부 받아오기
		String result = dao.signup(user_id, user_pw, user_name, user_email,
				user_phone, user_postcode, user_address, user_address_detail, user_marketing);
		
		//출력 화면으로 결과값 전송
		model.addAttribute("result", result);
	}

}
