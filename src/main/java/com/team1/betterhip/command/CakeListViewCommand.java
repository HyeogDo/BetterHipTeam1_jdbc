package com.team1.betterhip.command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.team1.betterhip.dao.CakeListDao;
import com.team1.betterhip.dto.CakeListDto;

public class CakeListViewCommand implements BetterHipCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		
		//���� ����
		//Request ����
		Map<String, Object> map = model.asMap();
		request = (HttpServletRequest) map.get("request");
		
		//Dao ����
		CakeListDao dao = new CakeListDao();
		
		//DAO���� �޾ƿ� ���� ����
		ArrayList<CakeListDto> temp = null;
		ArrayList<CakeListDto> dtos = new ArrayList<CakeListDto>();
		CakeListDto dto = null;
		
		//�̹��� ó���� ���� ���� ����
		String base64Image = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		
		//����¡�� ���� ����
		String order;
		String search;
		String search_temp;
		 //�� �������� ���� ������ ��ġ ����
		int len;
		 //�� ������ �ڽ� ���� ���� ������ ����
		int pageBoxLen = 3;
		 //���� ��ġ�� ������ �޾ƿ���
		int curPage;
		 //�� ������ ��
		int totalPage;
		 //DB���� �޾ƿö� �����ϴ� �ε��� ��ȣ
		int startNum;
		 //���� ������ �ڽ�
		int curPageBox;
		 //���� ������ �ڽ� �� ù ������
		int pageBoxStartNum;
		 //���� ������ �ڽ� ����
		int curPageBoxLen;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage").toString());
			len = Integer.parseInt(request.getParameter("len").toString());
			order = request.getParameter("order").toString();
			search_temp = request.getParameter("search").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			curPage = 1;
			len = 4;
			order = "cake_id";
			search_temp = "";
		}
		search = "where cake_name like '%" + search_temp + "%'";
	
		
		 //���������������� ���� ������ ����ó��
		  //�� ������ ���� ������ ���ο� ���� �޶���
		if ((dao.getTotalRows(search) % len) == 0) {
			totalPage = (dao.getTotalRows(search) / len);
		} else {
			totalPage = (dao.getTotalRows(search) / len) + 1;
		}
		
		if (curPage > totalPage) {
			curPage = totalPage;
		}
		//1���������� ���� ������ ����ó��
		if (curPage <= 0) {
			curPage = 1;
		}
		 //�����ͺ��̽����� �޾ƿö� �����ϴ� �ε��� ��ȣ
		startNum = len * (curPage - 1);
		 //���� �������ڽ�
		curPageBox = ((curPage - 1) / pageBoxLen) + 1;
		 //���� ������ �ڽ� �� ù ������
		pageBoxStartNum = pageBoxLen * (curPageBox - 1) + 1;
		 //���� ������ �ڽ� ���� ó��(������ ���������� pageboxlen���� ���̰� ª�� ��츦 ���ؼ�)
		curPageBoxLen = pageBoxLen;
		if (((totalPage % pageBoxLen != 0) && (curPageBox == (totalPage / pageBoxLen) + 1)) || (totalPage < pageBoxLen )){
			curPageBoxLen = totalPage % pageBoxLen;			
		} 
		
				
				
		//Dao �Լ��� DB�� ����Ʈ �޾ƿ���
		temp = dao.list(startNum, len, order, search);
		for(int i = 0; i < temp.size(); i++) {
			try {
				// blob ���� ���� -> bytes
				inputStream = temp.get(i).getCake_img().getBinaryStream();
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				
				//����Ʈ -> ���ڿ�
				base64Image = Base64.getEncoder().encodeToString(imageBytes);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(inputStream != null) inputStream.close();
					if(outputStream != null) outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dto = new CakeListDto(temp.get(i).getCake_id(), 
						temp.get(i).getCake_name(), 
						temp.get(i).getCake_saleprice(), 
						temp.get(i).getCake_status(), 
						base64Image);	
				dtos.add(dto);
				
			}
		}
		
		//�ѱ� �����͵�
		model.addAttribute("list", dtos);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageBoxLen", pageBoxLen);
		model.addAttribute("curPageBoxLen", curPageBoxLen);
		model.addAttribute("pageBoxStartNum", pageBoxStartNum);
		model.addAttribute("len", len);
		model.addAttribute("order", order);
		model.addAttribute("search", search_temp);
	}


}
