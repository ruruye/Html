package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.mybatis.config.MyBatisSqlSessionFactory;

public class BoardDaoImpl implements IBoardDao{

	// 자기 자신의 클래스 객체 선언, 생성, 리턴
	private static IBoardDao dao;
	
	public static IBoardDao getDao() {
		if(dao==null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public List<BoardVO> listPerPage(Map<String, Object> map) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		// 1 리턴 변수 선언
		List<BoardVO> list = null;
		
		// 2 list = mapper 실행
		try {
			list = sqlSession.selectList("board.listPerPage", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		//  3결과 리턴
		return list;
	}

	@Override
	public int totalCount(Map<String, Object> map) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		// 1 리턴 변수 선언
		int count = 0;
		
		// 2 count = mapper 실행
		try {
			count = sqlSession.selectOne("board.totalCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		//  3결과 리턴
		return count;
	}


	@Override
	public int insertBoard(BoardVO vo) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.insert("board.insertBoard", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}


	@Override
	public int updateBoard(BoardVO vo) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.update("board.updateBoard", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}


	@Override
	public int deleteBoard(int num) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.delete("board.deleteBoard", num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}


	@Override
	public int updateHit(int num) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.update("board.updateHit", num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}
	
	
	@Override
	public int insertReply(ReplyVO vo) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.insert("reply.insertReply", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}


	@Override
	public int updateReply(ReplyVO vo) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.update("reply.updateReply", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}


	@Override
	public int deleteReply(int renum) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int res = 0;
		
		try {
			res = sqlSession.delete("reply.deleteReply", renum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return res;
	}


	@Override
	public List<ReplyVO> listReply(int bonum) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		// 1 리턴 변수 선언
		List<ReplyVO> list = null;
		
		// 2 list = mapper 실행
		try {
			list = sqlSession.selectList("reply.listReply", bonum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		//  3결과 리턴
		return list;
	}


}
