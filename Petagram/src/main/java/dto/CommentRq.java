package dto;


public class CommentRq {
	private String content;
    private int memberId;
    private int postId;
    
    public CommentRq() {
		super();
	}

	public CommentRq(String content, int memberId, int postId) {
		super();
		this.content = content;
		this.memberId = memberId;
		this.postId = postId;
	}
    
    public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}    
    
}
