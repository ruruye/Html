package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;
import kr.or.ddit.lprod.vo.LprodVO;

/**
 * Servlet implementation class LprodList
 */
@WebServlet("/LprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LprodList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전송요청시 데이터 받기 - x
		
		// service 객체 얻기
		ILprodService service = LprodServiceImpl.getService();
		
		// service메서도 호출 - 결과
		List<LprodVO> list = service.getAlllprod();
		
		// 결과값을 request에 저장
		request.setAttribute("reqLprod", list);
		
		// view 페이지로 이동 - html태그를 이용하여 출력/ json데이터 생성
		request.getRequestDispatcher("/0530/lprodList.jsp").forward(request, response);
		
	}

}
