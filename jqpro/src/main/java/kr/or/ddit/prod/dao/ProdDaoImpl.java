package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


import kr.or.ddit.mybatis.config.MyBatisSqlSessionFactory;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdDaoImpl implements IProdDao{
	
	// 자기자신의 객체를 생성하여 리턴
	private static IProdDao dao;
	
	public static IProdDao getDao() {
		if(dao==null) dao = new ProdDaoImpl();
		
		return dao;
	}
	

	@Override
	public List<ProdVO> lguProd(String lgu) {
		// sqlSession생성
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		// 리턴타입변수생성
		List<ProdVO> list = null;
		
		
		try {
			// mapper 실행
			list = sqlSession.selectList("prod.lguProd", lgu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		
		return list;
	}

	@Override
	public ProdVO idProd(String id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession(); 
		ProdVO vo = null;
		
		try {
			// sqlSession으로 mapper수행
			vo = sqlSession.selectOne("prod.idProd", id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return vo;
	}
	
}
