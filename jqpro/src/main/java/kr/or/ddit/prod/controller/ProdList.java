package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * Servlet implementation class ProdList
 */
@WebServlet("/ProdList.do")
public class ProdList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdList() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송요청시 전달 데이터 받기 - gu
		String lgu = request.getParameter("gu");
		
		// service객체 얻기
		IProdService service = ProdServiceImpl.getService();
		
		// service메소드 호출하기 - 결과값 가져오기
		List<ProdVO> list = service.lguProd(lgu);
		
		// 결과값을 request에 저장하기
		request.setAttribute("reqProd", list);
		
		// view페이지로 이동하기 /0530/prodList.jsp json데이터 생성
		request.getRequestDispatcher("/0530/prodList.jsp").forward(request, response);
		
	}

}
