package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;

/**
 * Servlet implementation class ZipSearch
 */
@WebServlet("/ZipSearch.do")
public class ZipSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// 요청시 전송데이터 받기 - dong
		String searchDong = request.getParameter("dong");
		
		// service 객체얻기
		IMemberService service = MemberServiceImpl.getService();
			
		// service 메서도 호출 - 결과값 얻기 - String
		List<ZipVO> list = service.openzip(searchDong);
		
		// 결과값을 request에 저장하기
		request.setAttribute("reqZip", list);
		
		// View페이지로 이동 - json데이터 생성
		request.getRequestDispatcher("/0601/zipSearch.jsp").forward(request, response);
	
	}

}
