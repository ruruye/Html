package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;

/**
 * Servlet implementation class BuyerDetail
 */
@WebServlet("/BuyerDetail.do")
public class BuyerDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//전송시 데이터를 받는다
		String buyerId = request.getParameter("id");
		
		// service객체 얻는다
		IBuyerService service = BuyerServiceImpl.getService();
		
		// service 메소드 호출 -> 수행결과를 얻는다
		BuyerVO vo = service.buyerDetail(buyerId);
		
		// request객체로 결과를 저장한다
		request.setAttribute("requestDetail", vo);
		
		// view페이지로 이동하여 결과 생성 - 수행결과로 응답데이터를 생성 - json 배열
		request.getRequestDispatcher("/0526/detail.jsp").forward(request, response);
			
	}

}
