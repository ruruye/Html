<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
// 서블릿에서 저장한 데이터 꺼내기
String res = (String)request.getAttribute("reqId");
if(res==null){
	// 사용가능
%>

{
	"flag" : "사용가능 id"
}

<%
}else{
	// 사용불가능
%>

{
	"flag" : "사용불가능 id"
}



<%	
}
%>