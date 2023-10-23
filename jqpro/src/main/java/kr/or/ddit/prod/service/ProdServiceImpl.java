package kr.or.ddit.prod.service;

import java.util.List;




import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements IProdService {

	// dao 객체필요
	private  IProdDao dao;
	private static IProdService service;
	
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getDao();
		
	}
	
	// 자기자신
	public static IProdService getService() {
		if(service==null) {
			service = new ProdServiceImpl();
		}
		return service;
	}
	
	
	
	@Override
	public List<ProdVO> lguProd(String lgu) {
		
		
		// 리턴타입변수생성
		/*
		 * List<ProdVO> list = null;
		 * 
		 * list = dao.lguProd(lgu);
		 * 
		 * 
		 * return list;
		 */
		
		return dao.lguProd(lgu);
	}

	@Override
	public ProdVO idProd(String id) {
	
		/*
		 * ProdVO vo =null;
		 * 
		 * vo = dao.idProd(id);
		 * 
		 * return vo;
		 */
		
		return dao.idProd(id);
		
	}
}
