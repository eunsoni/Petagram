package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.CommentDTO;
import dto.ImageDTO;
import dto.PostDTO;

@Repository
public class ReadPostOneDAO {
	
	@Autowired
	SqlSession session;
	
	/**
	 * 게시물 정보 조회
	 * @param postId
	 * @return 게시물 정보
	 */
	public PostDTO selectPostDetail(int postId) {
		return session.selectOne("selectPostDetail", postId);
	}
	
	/**
	 * 작성자명 조회
	 * @param memberId
	 * @return 작성자명
	 */
	public String selectMemberName(int memberId) {
		return session.selectOne("selectMemberName", memberId);
	}
	
	/**
	 * 댓글 조회
	 * @param postId
	 * @return 댓글 정보
	 */
	public List<CommentDTO> selectComment(int postId){
		return session.selectList("selectComment", postId);
	}
	
	/**
	 * 이미지 조회
	 * @param postId
	 * @return 이미지 리스트
	 */
	public List<ImageDTO> selectImage(int postId){
		return session.selectList("selectImage", postId);
	}
	
	/**
	 * DB에 댓글 저장
	 * @param content
	 * @param memberId
	 * @param postId
	 * @return 추가 행 개수
	 */
	public int insertComment(String content, int memberId, int postId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("content", content);
		map.put("memberId", memberId);
		map.put("postId", postId);
		
		return session.insert("insertComment", map);
	}
	
	/**
	 * DB에서 댓글 삭제
	 * @param postId
	 * @return 삭제 행 개수
	 */
	public int deleteComment(int commentId) {
		return session.delete("deleteComment", commentId);
	}
	
	
	/**
	 * 좋아요 조회
	 * @param postId
	 * @param memberId
	 * @return 클릭했으면 1, 아니면 0 
	 */
	public int selectLike(int postId, int memberId) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("postId", postId);
		map.put("memberId", memberId);
		
		return session.selectOne("selectLike", map);
	}
	
	/**
	 * 좋아요 삭제
	 * @param postId
	 * @param memberId
	 * @return
	 */
	public int deleteLike(int postId, int memberId) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("postId", postId);
		map.put("memberId", memberId);
		
		return session.delete("deleteLike", map);
	}
	
	/**
	 * 좋아요 추가
	 * @param postId
	 * @param memberId
	 * @return
	 */
	public int insertLike(int postId, int memberId) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("postId", postId);
		map.put("memberId", memberId);
		
		return session.insert("insertLike", map);
	}
	
	/**
	 * 게시글 좋아요 개수 조회
	 * @param postId
	 * @return
	 */
	public int selectLikeAll(int postId) {
		return session.selectOne("selectLikeAll", postId);
	}
	
	/**
	 * 게시물 삭제
	 * @param postId
	 * @return
	 */
	public int deletePost(int postId) {
		return session.delete("deletePost", postId);
	}
	
}
