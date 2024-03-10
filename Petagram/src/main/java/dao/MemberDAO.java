package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.MemberDTO;
import dto.PetDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSession session;

	// search
	public List<MemberDTO> searchList(HashMap<String, String> map) {
		return session.selectList("searchList", map);
	}

	// registermember
	// 1.memberid 찾는 메서드
	public String selectMemberid(int memberId) {
		return session.selectOne("searchMemberid", memberId);
	}

	// 2.insert members, pet
	public int insertMember(MemberDTO dto) {
		return session.insert("insertMember", dto);
	}

	public int insertPet(PetDTO pet) {
		return session.insert("insertPet", pet);
	}

	// updatemember
	public int updateMember(MemberDTO dto) {
		return session.update("updateMember", dto);
	}

	public int updatePet(PetDTO pet) {
		return session.update("updatePet", pet);
	}

	public MemberDTO searchMemberid(int memberid) {
		return session.selectOne("searchMemberid", memberid);
	}

	public MemberDTO getMemberById(int memberid) {
		return session.selectOne("getMemberById", memberid);
	}

	// loginmember
	// 1.같은 아이디 폰번호 이메일 있는 객체 반환하는 메서드
	public MemberDTO oneIdMember(String id) {
		return session.selectOne("oneIdMember", id);
	}

	public MemberDTO onePhoneMember(String phone) {
		return session.selectOne("onePhoneMember", phone);
	}

	public MemberDTO oneEmailMember(String email) {
		return session.selectOne("oneEmailMember", email);
	}

	public MemberDTO oneMemberidMember(int memberid) {
		return session.selectOne("oneMemberidMember", memberid);
	}

	public PetDTO oneMemberidPet(int memberid) {
		return session.selectOne("oneMemberidPet", memberid);
	}

	public List<MemberDTO> memberList() {
		return session.selectList("memberList");
	}

	public List<MemberDTO> memberAll() {
		return session.selectList("memberAll");
	}
	
	public Integer memberExists(String id, String pw) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("pw", pw);
		return session.selectOne("memberExists", params);
	}
	
	public int getMemberid(String id) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return session.selectOne("getMemberid", params);
	}


}
