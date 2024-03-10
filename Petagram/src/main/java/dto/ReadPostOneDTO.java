package dto;

import java.util.List;

public class ReadPostOneDTO {
	private String memberName;
	private PostDTO postInfo;
	private int likeCnt;
	private List<CommentDTO> comments;
	
	public ReadPostOneDTO(String memberName, PostDTO postInfo, int likeCnt, List<CommentDTO> comments) {
		super();
		this.memberName = memberName;
		this.postInfo = postInfo;
		this.likeCnt = likeCnt;
		this.comments = comments;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public PostDTO getPostInfo() {
		return postInfo;
	}

	public void setPostInfo(PostDTO postInfo) {
		this.postInfo = postInfo;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
}
