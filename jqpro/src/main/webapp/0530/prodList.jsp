<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
// 서블릿에서 저장한 데이터 꺼내기
List<ProdVO> list = (List<ProdVO>)request.getAttribute("reqProd");
if(list != null && list.size() > 0){
%>	

	{
		"flag" : "ok",
		"datas" : [ <%-- json객체 배열 시작 --%>
		

<%
	for(int i=0; i<list.size(); i++){
		ProdVO vo = list.get(i);
		if(i>0) out.print(",");
	
%>

	{
		"id" : "<%= vo.getProd_id() %>",
		"name" : "<%= vo.getProd_name() %>"
	}

<%	
	} // for
%>

	] <%-- json객체 배열 끝 --%>
	
} 

<%	
 }else{ // if
%>
	 
	{
   		"flag" : "no"
	}
	 
<%	 
 } // else 끝
%>

