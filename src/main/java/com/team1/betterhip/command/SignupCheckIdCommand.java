package com.team1.betterhip.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.team1.betterhip.dao.SignupDao;

public class SignupCheckIdCommand implements BetterHipCommand {
	
	


	@Override
	public void execute(HttpServletRequest request,Model model) {
		// TODO Auto-generated method stub
		
		//Request ����
		Map<String, Object> map = model.asMap();
		request= (HttpServletRequest) map.get("request");
		
		//Dao ����
		SignupDao dao = new SignupDao();
		
		//���� ����
		String user_id = request.getParameter("user_id");
		
		//Dao �Լ��� DB�� �ߺ� Ȯ�� �� ����� �޾ƿ���
		int result = dao.signupCheckId(user_id);
		
		//���� ����� session�� ����
		if (result == 1) {
			model.addAttribute("result", "�̹� ������� ���̵� �Դϴ�.");
		}else {
			model.addAttribute("result", "��� ������ ���̵� �Դϴ�.");
		}
	}

}
