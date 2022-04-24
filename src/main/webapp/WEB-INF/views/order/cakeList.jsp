<%@page import="java.util.ArrayList"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String order = request.getAttribute("order").toString();
	String search = request.getAttribute("search").toString();
	// 페이징 용 데이터
	int curPage = Integer.parseInt(request.getAttribute("curPage").toString());
	int pageBoxLen = Integer.parseInt(request.getAttribute("pageBoxLen").toString());
	int curPageBoxLen = Integer.parseInt(request.getAttribute("curPageBoxLen").toString());
	int pageBoxStartNum = Integer.parseInt(request.getAttribute("pageBoxStartNum").toString());
	int len = Integer.parseInt(request.getAttribute("len").toString());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">

<link rel="stylesheet" href="../BetterHip/assets/css/login_find.css">
<link rel="shortcut icon" type="image/x-icon" href="../BetterHip/assets/img/favicon.ico">


<link rel="stylesheet" href="../BetterHip/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="../BetterHip/assets/css/templatemo.css">
<link rel="stylesheet" href="../BetterHip/assets/css/custom.css">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="../BetterHip/assets/css/fontawesome.min.css">

<title>Better Hip 케이크 리스트</title>

</head>

	<body>
	<div id="wrap">
	<header>
		<div class="top-header">
			<div class="container clearfix">
			<div class="member-area">
				<a href="loginForm.do">로그인</a>
				<span class="bar">|</span>
				<a href="signup.do">회원가입</a>
				<span class="bar">|</span>
				<a href="#!">장바구니</a>
				<span class="bar">|</span>
				<a href="#!">My Page</a>
			</div>
			</div>
		</div>
		<div class="main-header">
			<div class="container clearfix">
			<h1 class="logo">
				<a href="#!">
				<img src="../BetterHip/assets/img/logo.png" alt="로고" width="120">
				</a>
			</h1>
			<h1 class="logo_text">
				<a href="main.do">Better Hip</a>
			</h1>
			<div class="gnb">
				<ul class="clearfix">
					<li class="nav-item info">
						<a href="#!" class="nav-link">이용안내</a>
						<div class="dropdown">
						<ul>
							<li>
							<a href="#!">케이크 안내</a>
							</li>
							<li>
							<a href="#!">픽업 방법</a>
							</li>
							<li>
							<a href="#!">이용 안내</a>
							</li>
						</ul>
						</div>
					</li>
					<li class="nav-item cake">
						<a href="main.doß" class="nav-link">케이크 주문</a>
					</li>
					<li class="nav-item intro">
						<a href="#!" class="nav-link">소개</a>
					</li>
					<li class="nav-item notice">
						<a href="#!" class="nav-link">공지사항</a>
					</li>
				</ul>
			</div>
			</div>
		</div>
	</header>
	</div>	
	
<!-- 	레터링 케이크 리스트 -->
		<div align="center">
			<div align="center">
				<h2 style="padding-top: 3%;"><b>레터링 케이크</b></h2>
				<h4 style="padding: 1%;">선물하고 싶은 메세지를 알려주세요. 다해줄게요.</h4>
			</div>
			<br>
			<div  style="position: relative; width:1260px">
				<div align="left">
					<form action="cakeList" method="post">
						<select name="order">
							<option value="cake_saleprice desc" <%=order.equals("cake_saleprice desc")?"selected='selected'":"" %>>높은 가격 순</option>
							<option value="cake_saleprice" <%=order.equals("cake_saleprice")?"selected='selected'":"" %>>낮은 가격 순</option>
						</select>
						<select name="len" >
							<option value="4" <%=request.getAttribute("len").toString().equals("4")?"selected='selected'":"" %>>4개씩 보기</option>
							<option value="8" <%=request.getAttribute("len").toString().equals("8")?"selected='selected'":"" %>>8개씩 보기</option>
						</select>
						<input type="hidden" name="curPage" value="1">
						<input type="hidden" name="search" value="<%=search %>">
						<input type="submit" value="적용">
					</form>
				</div>
				<div align="right">
					<form action="cakeList" method="post">
						<input type="text" size="15" placeholder="제품명 검색" name="search" value="<%=search %>"> <input type="submit" value="검색">
						<input type="hidden" name="curPage" value="1">
						<input type="hidden" name="len" value="<%=len %>">
						<input type="hidden" name="order" value="<%=order %>">
					</form>
				</div>
				<div>
						<table>
							<tr>
							<c:forEach items="${list}" var="dto" varStatus="status">
									
								<td><a href="cakeChoice?cake_id=${dto.cake_id }" ><img src="data:cake_img/png;base64, ${dto.base64Image }" width = "200" height="200"><br>
								${dto.cake_name }<br>
								${dto.cake_saleprice }</a></td>
								<c:if test="${status.index % 4 == 3 }">
									</tr><tr>
								</c:if>
							</c:forEach>
						</table>
				</div>
				<table>
					<tr>
						<td><form action="cakeList" method="post"><input type="hidden" name="curPage" value="<%=pageBoxStartNum - pageBoxLen %>">
						<input type="hidden" name="len" value="<%=len %>">
						<input type="hidden" name="order" value="<%=order %>">
						<input type="hidden" name="search" value="<%=search %>">
						<input type="submit" value="<<"> </form></td>
						<td><form action="cakeList" method="post"><input type="hidden" name="curPage" value="<%=curPage - 1 %>">
						<input type="hidden" name="len" value="<%=len %>">
						<input type="hidden" name="order" value="<%=order %>">
						<input type="hidden" name="search" value="<%=search %>">
						<input type="submit" value="이전"> </form></td>
						<% for (int i = pageBoxStartNum - 1; i < pageBoxStartNum + curPageBoxLen - 1; i++) {
							%><td><form action="cakeList" method="post"><input type="submit" name="curPage" value="<%=i + 1%>">
							<input type="hidden" name="len" value="<%=len %>">
							<input type="hidden" name="order" value="<%=order %>">
							<input type="hidden" name="search" value="<%=search %>"></form></td> <% 
								}
						%>
						<td><form action="cakeList" method="post"><input type="hidden" name="curPage" value="<%=curPage + 1 %>">
						<input type="hidden" name="len" value="<%=len %>">
						<input type="hidden" name="order" value="<%=order %>">
						<input type="hidden" name="search" value="<%=search %>">
						<input type="submit" value="다음"> </form></td>
						<td><form action="cakeList" method="post"><input type="hidden" name="curPage" value="<%=pageBoxStartNum + pageBoxLen %>">
						<input type="hidden" name="len" value="<%=len %>">
						<input type="hidden" name="order" value="<%=order %>">
						<input type="hidden" name="search" value="<%=search %>">
						<input type="submit" value=">>"> </form></td>
					</tr>
				</table>
			</div>
		</div>
		
<!-- Start Footer -->
    <footer class="bg-dark" id="footer">
        <div class="footer_container">

            <div class="row_footer">                        
                <div class="w-100 bg-black py-3">
                    <ul class="list-unstyled text-light footer-link-list">
                        <li><h5>Better Hip</h5></li>
                        <!-- <li><a class="text-decoration-none" href="#">이용약관      |      </a>
                        <a class="text-decoration-none" href="#">개인정보처리방침      |      </a>
                        <a class="text-decoration-none" href="#">이메일무단수집거부      |      </a></li> -->
                        <p class="text-center text-light">
                            <a class="text-decoration-none" href="#">이용약관      |      </a>
                            <a class="text-decoration-none" href="#">개인정보처리방침      |      </a>
                            <a class="text-decoration-none" href="#">이메일무단수집거부      |      </a>
                        </p>
                        <li><a>통신판매신고번호:2021-서울서초-0413</a></li>
                        <li><a>사업자등록번호: 000-00-00000 주식회사 배러힙</a></li>
                        <li><a>주소: 서울특별시 서초구 서초동 1305 서산빌딩</a></li>
                        <li><a>대표자: 원경호</a></li>
                        <li><a>전화번호: 02-000-0000</a></li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="w-100 bg-black">
            <div class="footer_2_container">
                <div class="row pt-2">
                    <div class="col-12">
                        <p class="text-left text-light">
                            Copyright &copy; 배러힙 2022 Better Hip
                            | Designed by <a rel="sponsored" href="https://betterhip.dothome.com" target="_blank">Betterhip</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </footer>
    <!-- End Footer -->

<!-- Start Script -->
<script src="assets/js/jquery-1.11.0.min.js"></script>
<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="assets/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/templatemo.js"></script>
<script src="assets/js/custom.js"></script>
<!-- End Script -->
	</body>
</html>