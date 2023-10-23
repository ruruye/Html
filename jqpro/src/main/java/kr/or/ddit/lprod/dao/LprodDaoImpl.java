package kr.or.ddit.lprod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.lprod.vo.LprodVO;
import kr.or.ddit.mybatis.config.MyBatisSqlSessionFactory;
import kr.or.ddit.prod.vo.ProdVO;


public class LprodDaoImpl implements ILprodDao{
	
	// 자기자신의 객체를 생성하여 리턴
		private static ILprodDao dao;
		
		public static ILprodDao getDao() {
			if(dao==null) dao = new LprodDaoImpl();
			
			return dao;
		}

	@Override
	public List<LprodVO> getAlllprod() {
		// TODO Auto-generated method stub
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		// 리턴타입변수생성
		List<LprodVO> list = null;
		
		
		try {
			// mapper 실행
			list = sqlSession.selectList("lprod.getAlllprod");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		
		return list;
	}

}
