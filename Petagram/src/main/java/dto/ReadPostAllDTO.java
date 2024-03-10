package dto;

import java.util.List;

public class ReadPostAllDTO {
	private List<PostDTO> posts;
	private List<MemberDTO> members;
	

	public ReadPostAllDTO() {
		
	}
	
	public ReadPostAllDTO(List<PostDTO> posts, List<MemberDTO> members) {
		this.posts = posts;
		this.members = members;
	}
	
	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}
	
	
	
}
