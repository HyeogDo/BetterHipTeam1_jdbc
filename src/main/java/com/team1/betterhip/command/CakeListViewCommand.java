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
		
		//변수 선언
		//Request 선언
		Map<String, Object> map = model.asMap();
		request = (HttpServletRequest) map.get("request");
		
		//Dao 선언
		CakeListDao dao = new CakeListDao();
		
		//DAO에서 받아올 변수 선언
		ArrayList<CakeListDto> temp = null;
		ArrayList<CakeListDto> dtos = new ArrayList<CakeListDto>();
		CakeListDto dto = null;
		
		//이미지 처리를 위한 변수 선언
		String base64Image = null;
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		
		//페이징을 위한 변수
		String order;
		String search;
		String search_temp;
		 //한 페이지에 들어가는 데이터 뭉치 개수
		int len;
		 //한 페이지 박스 내에 들어가는 페이지 개수
		int pageBoxLen = 3;
		 //현재 위치한 페이지 받아오기
		int curPage;
		 //총 페이지 수
		int totalPage;
		 //DB에서 받아올때 시작하는 인덱스 번호
		int startNum;
		 //현재 페이지 박스
		int curPageBox;
		 //현재 페이지 박스 내 첫 페이지
		int pageBoxStartNum;
		 //현재 페이지 박스 길이
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
	
		
		 //마지막페이지에서 다음 누를시 예외처리
		  //총 페이지 수는 나머지 여부에 따라 달라짐
		if ((dao.getTotalRows(search) % len) == 0) {
			totalPage = (dao.getTotalRows(search) / len);
		} else {
			totalPage = (dao.getTotalRows(search) / len) + 1;
		}
		
		if (curPage > totalPage) {
			curPage = totalPage;
		}
		//1페이지에서 이전 누를시 예외처리
		if (curPage <= 0) {
			curPage = 1;
		}
		 //데이터베이스에서 받아올때 시작하는 인덱스 번호
		startNum = len * (curPage - 1);
		 //현재 페이지박스
		curPageBox = ((curPage - 1) / pageBoxLen) + 1;
		 //현재 페이지 박스 내 첫 페이지
		pageBoxStartNum = pageBoxLen * (curPageBox - 1) + 1;
		 //현재 페이지 박스 길이 처리(마지막 페이지에서 pageboxlen보다 길이가 짧은 경우를 위해서)
		curPageBoxLen = pageBoxLen;
		if (((totalPage % pageBoxLen != 0) && (curPageBox == (totalPage / pageBoxLen) + 1)) || (totalPage < pageBoxLen )){
			curPageBoxLen = totalPage % pageBoxLen;			
		} 
		
				
				
		//Dao 함수로 DB에 리스트 받아오기
		temp = dao.list(startNum, len, order, search);
		for(int i = 0; i < temp.size(); i++) {
			try {
				// blob 파일 내용 -> bytes
				inputStream = temp.get(i).getCake_img().getBinaryStream();
				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				
				//바이트 -> 문자열
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
		
		//넘길 데이터들
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
