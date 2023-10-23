package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public class MemberServiceImpl implements IMemberService{
	
	private static IMemberService service;
	private IMemberDao dao;
	
	// dao객체 얻어오기
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getDao();
	}
	
	// 자기자신의 객체생성해서 리턴
	public static IMemberService getService() {
		if(service==null)
			service = new MemberServiceImpl();
		return service;
	}
	
	@Override
	public String checkId(String id) {
		
		return dao.checkId(id);
	}

	@Override
	public int insertMember(MemberVO vo) {

		return dao.insertMember(vo);
	}

	@Override
	public List<ZipVO> openzip(String num) {
		
		return dao.openzip(num);
	}

}
