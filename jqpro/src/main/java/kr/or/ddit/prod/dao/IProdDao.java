package kr.or.ddit.prod.dao;

import java.util.List;


import kr.or.ddit.prod.vo.ProdVO;

public interface IProdDao {
	
	public List<ProdVO> lguProd(String lgu);
	
	public ProdVO idProd(String id);
}
