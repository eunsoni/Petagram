package dto;

public class LikeRq {
	private int postId;
	private int memberId;
	
	public LikeRq() {
		super();
	}
	
	public LikeRq(int postId, int memberId) {
		super();
		this.postId = postId;
		this.memberId = memberId;
	}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
