<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h1{
	color : red;
}

table{
	border : 2px solid blue;
}

td{
	width : 300px;
	height: 50px;
	text-align: center;
}
</style>

</head>
<body>
	<h1>JSP : Java Server Page</h1>
	
	<%
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("id");
		String userTel = request.getParameter("tel");
		String userMail = request.getParameter("mail");
		String userArea= request.getParameter("area");
		
		
		String scar = request.getParameter("carsingle");
		String[] mcar = request.getParameterValues("carmulti");
		
		String str="";
		for(String car : mcar){
			str += car + "&nbsp;&nbsp;";
		}
		
		//UserArea 값에는 엔터기호(\r\n)가 포함
		//\n을 <br>태그로 변환
		String area = userArea.replaceAll("\n", "<br>");
	
	%>
	
<table border="1">
	<tr>
		<td>아이디</td>
		<td><%= userId %></td>
	</tr>
	
	<tr>
		<td>전화번호</td>
		<td><%= userTel %></td>
	</tr>
	
	<tr>
		<td>이메일</td>
		<td><%= userMail %></td>
	</tr>
	
	<tr>
		<td>고객의 의견</td>
		<td><%= area  %></td>
	</tr>
	
	<tr>
		<td>자동차</td>
		<td><%= scar  %></td>
	</tr>
	
	<tr>
		<td>자동차</td>
		<td><%= str  %></td>
	</tr>
</table>
</body>
</html>