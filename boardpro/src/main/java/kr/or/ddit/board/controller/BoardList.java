package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/BoardList.do")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 1. 요청시 전송 데이터 받기 - 페이지 번호, 검색시 stype, sword
		// page => 최초실행시 무조건 1페이지
		int page = Integer.parseInt(request.getParameter("page"));
		
		// stype, sword => 최초 실행시 값은 null
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		
		// 2. service객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		// listPerPage(map)를 호출하기 위한 준비작업
		PageVO pvo = service.pageInfo(page, stype, sword);
		// start, end, startPage, endPage, totalPage가 들어있음
		
		
		// 3. service메소드 - listPerPage(map)호출하기 - 결과값 받기
		Map<String, Object> map = new HashMap<>();
		map.put("start", pvo.getStart());
		map.put("end", pvo.getEnd());
		map.put("stype", stype);
		map.put("sword", sword);
		
		List<BoardVO> list = service.listPerPage(map);
		
		// 4. 결과값을 request에 저장하기
		request.setAttribute("reqlist", list);
		request.setAttribute("reqpvo", pvo);
		
		
		// 5. view 페이지로 이동
		request.getRequestDispatcher("/view/list.jsp").forward(request, response);
		
		
		
	}

}
