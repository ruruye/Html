package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;
import kr.or.ddit.mybatis.config.MyBatisSqlSessionFactory;


public class MemberDaoImpl implements IMemberDao{
	
	private static IMemberDao dao;
	
	// 자기자신의 객체생성해서 리턴
	public static IMemberDao getDao() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}

	@Override
	public String checkId(String id) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession(); 
		
		String res = null;
		
		try {
			res = sqlSession.selectOne("member.checkId", id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return res;
	}

	@Override
	public int insertMember(MemberVO vo) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession(); 
		
		int res = 0;
		
		try {
			res = sqlSession.insert("member.insertMember", vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return res;
	}

	@Override
	public List<ZipVO> openzip(String num) {
		
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		List<ZipVO> list = null;
		
		try {
			list = sqlSession.selectList("member.openzip", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return list;
	}

}
