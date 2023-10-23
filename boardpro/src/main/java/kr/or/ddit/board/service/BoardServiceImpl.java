package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.ReplyVO;


public class BoardServiceImpl implements IBoardService{
	
	private static IBoardService service;
	private IBoardDao dao;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getDao();
	}
	
	public static IBoardService getService() {
		if(service==null)
			service = new BoardServiceImpl();
		return service;
	}
	
	@Override
	public List<BoardVO> listPerPage(Map<String, Object> map) {
		return dao.listPerPage(map);
	}

	@Override
	public int totalCount(Map<String, Object> map) {
		return dao.totalCount(map);
	}

	@Override
	public PageVO pageInfo(int page, String stype, String sword) {
		
		// 전체 글 갯수 구하기
		Map<String, Object> map = new HashMap<>();
		map.put("stype", stype);
		map.put("sword", sword);
		
		int count = this.totalCount(map);
		
		// 전체페이지 수 구하기
		int totalPage = (int)Math.ceil((double) count / PageVO.getPerList());
		
		// start, end
		int start = (page-1) * PageVO.getPerList() + 1;
		int end = start + PageVO.getPerList() - 1;
		if(end > count) end = count;
		
		// startPage, endPage
		int perPage = PageVO.getPerPage();
		int startPage = ((page-1) / perPage * perPage) + 1;
		int endPage = startPage + perPage -1;
		if(endPage > totalPage) endPage  = totalPage;
		
		System.out.println("start=" + start);
		System.out.println("end=" + end);
		System.out.println("startPage=" + startPage);
		System.out.println("endPage=" + endPage);
		System.out.println("totalPage=" + totalPage);
		
		PageVO pvo = new PageVO();
		
		pvo.setStart(start);
		pvo.setEnd(end);
		pvo.setStartPage(startPage);
		pvo.setEndPage(endPage);
		pvo.setTotalPage(totalPage);
		
		
		return pvo;
	}

	@Override
	public int insertBoard(BoardVO vo) {
//		int res =0;
//		res = dao.insertBoard(vo);
//		return res;
		return dao.insertBoard(vo);
		
	}

	@Override
	public int updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(vo);
	}

	@Override
	public int deleteBoard(int num) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(num);
	}

	@Override
	public int updateHit(int num) {
		// TODO Auto-generated method stub
		return dao.updateHit(num);
	}

	@Override
	public int insertReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		return dao.insertReply(vo);
	}

	@Override
	public int updateReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		return dao.updateReply(vo);
	}

	@Override
	public int deleteReply(int renum) {
		// TODO Auto-generated method stub
		return dao.deleteReply(renum);
	}

	@Override
	public List<ReplyVO> listReply(int bonum) {
		// TODO Auto-generated method stub
		return dao.listReply(bonum);
	}

	

}
