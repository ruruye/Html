package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.vo.ProdVO;

public interface IProdService {
	
	// prod_id, prod_name을 출력
	// public List<ProdVO> 메소드이름(String 변수명);
	// public ProdVO 메소드이름(파라미터타입 변수명);
	public List<ProdVO> lguProd(String lgu);
	
	public ProdVO idProd(String id);
}
