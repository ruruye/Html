<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.mybatis.config.MyBatisSqlSessionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 전송데이터 받기

// DB처리 - sqlSession, sqlSessionFactory -mybatis-config.xml필요

SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();

List<MemberVO> list = sqlSession.selectList("member.getAllMember");
%>



[

<%
	for(int i=0; i<list.size(); i++){
		MemberVO vo = list.get(i);
		if(i>0) out.print(",");
%>

	{
		"id"   : "<%= vo.getMem_id() %>",
		"name" : "<%= vo.getMem_name() %>",
		"tel"  : "<%= vo.getMem_tel() %>",
		"addr" : "<%= vo.getMem_addr() %>"
	}

<% 	
	}

%>	

]