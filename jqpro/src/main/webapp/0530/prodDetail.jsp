<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 서블릿에서 저장한 데이터를 꺼내기
ProdVO vo = (ProdVO)request.getAttribute("reqDProd");
%>

{
	"prod_id" : "<%= vo.getProd_id() %>",
	"prod_name" : "<%= vo.getProd_name() %>",
	"prod_lgu" : "<%= vo.getProd_lgu() %>",
	"prod_price" : "<%= vo.getProd_price() %>",
	"prod_sale" : "<%= vo.getProd_sale() %>",
	"prod_size" : "<%= vo.getProd_size() %>",
	"prod_outline" : "<%= vo.getProd_outline() %>",
	"prod_detail" : "<%= vo.getProd_detail() %>",
	"prod_cost" : "<%= vo.getProd_cost() %>",
	"prod_buyer" : "<%= vo.getProd_buyer() %>",
	"prod_color" : "<%= vo.getProd_color() %>"
}