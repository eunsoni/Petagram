package dto;

import java.util.List;

public class HomeDTO {
	private List<PostDTO> posts;
	private List<MemberDTO> members;
	
	public HomeDTO() {}

	public HomeDTO(List<PostDTO> posts, List<MemberDTO> members) {
		this.posts = posts;
		this.members = members;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

}
