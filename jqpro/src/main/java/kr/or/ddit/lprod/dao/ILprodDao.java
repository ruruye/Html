package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.vo.LprodVO;

public interface ILprodDao {
	
	// lprod 리스트
	//public 리턴결과형 메소드 이름()
	public List<LprodVO> getAlllprod();
}
