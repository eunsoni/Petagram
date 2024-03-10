package dto;

public class MemberDTO {
	private int memberId;
	private String id;
	private String pw;
	private String memberName;
	private String phone;
	private String email;
	private Boolean authority;
	
	public MemberDTO() {
		super();
	}

	public MemberDTO(int memberId, String id, String pw, String memberName, String phone, String email,
			Boolean authority) {
		super();
		this.memberId = memberId;
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
		this.phone = phone;
		this.email = email;
		this.authority = authority;
	}
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getAuthority() {
		return authority;
	}
	public void setAuthority(Boolean authority) {
		this.authority = authority;
	}
}
