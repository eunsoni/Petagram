package dto;

public class PetDTO {
	String petname;
	int age;
	String pettype;
	String gender; 
	int memberid;

	public PetDTO() {
		
	}
	
	public PetDTO(String petname, int age, String pettype, String gender, int memberid) {
		super();
		this.petname = petname;
		this.age = age;
		this.pettype = pettype;
		this.gender = gender;
		this.memberid = memberid;

	
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPettype() {
		return pettype;
	}

	public void setPettype(String pettype) {
		this.pettype = pettype;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	

}
