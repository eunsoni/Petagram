package service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.MemberDTO;
import dto.PetDTO;

public interface MemberService {

	// registermember
	String selectMemberid();

	int insertMember(MemberDTO dto);

	int insertPet(PetDTO pet);

	String registerMember(MemberDTO dto);

	void registerPet(PetDTO pet);

	MemberDTO getMemberById(int memberid);

	MemberDTO oneMember(String id);

	Object loginMember(String id, String pw);
	
	MemberDTO oneIdMember(String id);

	MemberDTO onePhoneMember(String phone);

	MemberDTO oneEmailMember(String email);

	MemberDTO oneMemberidMember(int memberid);

	// MemberService.java
	void updatePet(PetDTO petDTO);

	void updateMember(MemberDTO memberDTO);

	public List<MemberDTO> memberList();

	public List<MemberDTO> searchList(HashMap<String, String> map);

	MemberDTO searchMemberid(int memberid);

	PetDTO oneMemberidPet(int memberid);

	MemberDTO settingDto(int memberid);

	PetDTO settingPet(int memberid);
	
	Integer memberExists(String id, String pw);
	
	int getMemberid(String id);

}
