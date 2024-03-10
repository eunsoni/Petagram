package dto;

import java.util.List;

public class NoticeDTO {
	
	private List<MemberDTO> members;
	private List<InformDTO> informs;
	
	public NoticeDTO() { }

	public NoticeDTO(List<MemberDTO> members, List<InformDTO> informs) {
		this.members = members;
		this.informs = informs;
	}

	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

	public List<InformDTO> getInforms() {
		return informs;
	}

	public void setInforms(List<InformDTO> informs) {
		this.informs = informs;
	}
	
	
}
