package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ReadPostOneDAO;
import dto.CommentDTO;
import dto.ImageDTO;
import dto.PostDTO;
import dto.ReadPostOneDTO;

@Service
public class ReadPostOneService {
	
	@Autowired
	ReadPostOneDAO dao;
		
	/**
	 * 게시물 상세 조회
	 * @param postId 게시글 식별번호
	 * @return 게시글 상세 정보
	 */
	public ReadPostOneDTO getReadPostOne(int postId) {
		PostDTO postDetail = dao.selectPostDetail(postId);		
		String memberName = dao.selectMemberName(postDetail.getMemberId());
		int likeCnt = dao.selectLikeAll(postId);
		List<CommentDTO> comments = dao.selectComment(postId);		
		
		return new ReadPostOneDTO(memberName, postDetail, likeCnt, comments);
	}
	
	/**
	 * 이미지 조회
	 * @param postId
	 * @return 이미지 리스트
	 */
	public List<ImageDTO> getImage(int postId){
		return dao.selectImage(postId);
	}
	
	/**
	 * DB에 댓글 저장
	 * @param content
	 * @param memberId
	 * @param postId
	 * @return 추가 행 개수
	 */
	public int insertComment(String content, int memberId, int postId) {
		return dao.insertComment(content, memberId, postId);
	}
	
	/**
	 * DB에서 댓글 삭제
	 * @param postId
	 * @return 삭제 행 개수
	 */
	public int deleteComment(int commentId) {
		return dao.deleteComment(commentId);
	}
	
	/**
	 * 좋아요 조회
	 * @param postId
	 * @param memberId
	 * @return
	 */
	public int selectLike(int postId, int memberId) {
		return dao.selectLike(postId, memberId);
	}
	
	/**
	 * 좋아요 삭제
	 * @param postId
	 * @param memberId
	 * @return
	 */
	public int deleteLike(int postId, int memberId) {
		return dao.deleteLike(postId, memberId);
	}
	
	/**
	 * 좋아요 추가
	 * @param postId
	 * @param memberId
	 * @return
	 */
	public int insertLike(int postId, int memberId) {
		return dao.insertLike(postId, memberId);
	}
	
	/**
	 * 게시물 좋아요 개수 조회
	 * @param postId
	 * @return
	 */
	public int selectLikeAll(int postId) {
		return dao.selectLikeAll(postId);
	}
	
	/**
	 * 게시물 삭제
	 * @param postId
	 * @return
	 */
	public int deletePost(int postId) {
		return dao.deletePost(postId);
	}
}
