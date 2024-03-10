package dto;

public class DeletePostRq {
	private int postId;
	
	public DeletePostRq() {
		super();
	}

	public DeletePostRq(int postId) {
		super();
		this.postId = postId;
	}

	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
}
