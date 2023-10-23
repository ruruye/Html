<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- jsp 주석 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
	<p>클라이언트의 요청(request)을 받아서 서버 내에서 처리하는 서버프로그램</p>
	<p>서버 내 처리 결과를 클라이언트로 응답(response)한다</p>

<%
	request.setCharacterEncoding("UTF-8");	

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	
	String gender = request.getParameter("gender");
	
	String[] likes = request.getParameterValues("like");
	String str = "";
	for(String like : likes){
		str += like +" ";
	}
	/*
	for(int i=0; i<likes.length; i++){
		str += likes[i] + " ";
	}
	*/
%>

<table border="1">
	<tr>
		<td>아이디</td>
		<td><%= userId %></td>
	</tr>
	
	<tr>
		<td>이름</td>
		<td><%= userName %></td>
	</tr>
	
	<tr>
		<td>성별</td>
		<td><%= gender %></td>
	</tr>
	
	<tr>
		<td>취미</td>
		<td><%= str  %></td>
	</tr>
</table>
	
</body>
</html>