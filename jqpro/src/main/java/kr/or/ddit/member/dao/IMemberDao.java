package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberDao {
	// 아이디 중복검사
		// public 리턴결과 메소드이름(파라미터타입 변수명)
		public String checkId(String id);
		
		// 저장하기
		public int insertMember(MemberVO vo);
		
		// 우편번호검색
		public List<ZipVO> openzip(String num);
}
