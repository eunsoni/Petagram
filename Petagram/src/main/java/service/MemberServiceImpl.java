package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDAO;
import dto.MemberDTO;
import dto.PetDTO;

@Service
public class MemberServiceImpl implements MemberService {
	int memberid;
	
	@Autowired
	MemberDAO dao;

//	=====updatemember	
	
	@Override
	public MemberDTO oneMemberidMember(int memberid) {
		return dao.oneMemberidMember(memberid);
	}
	@Override
	public PetDTO oneMemberidPet(int memberid) {
		return dao.oneMemberidPet(memberid);
	}
	@Override
    public MemberDTO getMemberById(int memberid) {
        return dao.getMemberById(memberid);
	}
	
	@Override
    public MemberDTO settingDto(int memberid) {
		return oneMemberidMember(memberid);
	}
	
	
	@Override
	public PetDTO settingPet(int memberid) {
		return oneMemberidPet(memberid);
	}
	
	
	@Override
    public void updatePet(PetDTO pet) {
       dao.updatePet(pet);
    }

	@Override
    public void updateMember(MemberDTO dto) {
        dao.updateMember(dto);
    }

	//=====registermembers
    @Override
    public String registerMember(MemberDTO dto) {
        // 아이디 중복 체크
        MemberDTO existingMember = dao.oneIdMember(dto.getId());
        if (existingMember != null) {
            return "아이디 중복";
        }

        // 전화번호 중복 체크
        existingMember = dao.onePhoneMember(dto.getPhone());
        if (existingMember != null) {
            return "전화번호 중복";
        }

        // 이메일 중복 체크
        existingMember = dao.oneEmailMember(dto.getEmail());
        if (existingMember != null) {
            return "이메일 중복";
        }
        
        System.out.println(dto.getId());
        System.out.println(dto.getMemberName());
        System.out.println(dto.getEmail());
        System.out.println(dto.getPhone());
        System.out.println(dto.getPw());

        // 중복이 없으면 회원 등록
        dao.insertMember(dto);       
        memberid = dto.getMemberId();
        return "회원 등록 완료";
    }

    @Override
    public void registerPet(PetDTO pet) {
        pet.setMemberid(memberid);
        dao.insertPet(pet);
    }
	    
	    
    //=====loginmembers
    
	@Override
	public Object loginMember(String id, String pw) {
	    // 아이디 중복 체크
	    MemberDTO sameIdDTO = dao.oneIdMember(id);
	    if(id!="" || pw!=null) {
		    if (sameIdDTO == null) {
		        return "존재하지 않는 아이디입니다.";
		    } else {
		        if (!sameIdDTO.getPw().equals(pw)) {
		            return "비밀번호를 확인해주세요.";
		        }
		        return "성공";
		    }
	    }else {
	    	return "로그인 전";	
	    }
	}
	
	@Override
	public Integer memberExists(String id, String pw) {
	    return dao.memberExists(id, pw);
	}
	
	@Override
	public int getMemberid(String id) {
	    return dao.getMemberid(id);
	}
		
	@Override
	public MemberDTO oneIdMember(String id) {
		return dao.oneIdMember(id);
	}
	@Override
	public MemberDTO onePhoneMember(String phone) {
		return dao.onePhoneMember(phone);
	}
	@Override
	public MemberDTO oneEmailMember(String email) {
		return dao.oneEmailMember(email);
	}
	
	    
	    
//	    @Override
//	    public MemberDTO searchMemberid(int memberid) {
//	        return dao.selectMemberid(memberid);
//	    }
//	    @Override
//	    public void updateMember(MemberDTO dto) {
//	        dao.updateMember(dto);
//	    }
//	    @Override
//	    public void updatePet(PetDTO pet) {
//	       dao.updatePet(pet);
//	    }
//	    
	

	@Override
	public MemberDTO oneMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> searchList(HashMap<String, String> map) {
		return dao.searchList(map);
	}

	@Override
	public String selectMemberid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPet(PetDTO pet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public MemberDTO searchMemberid(int memberid) {
        return dao.searchMemberid(memberid);
    }
	

}
