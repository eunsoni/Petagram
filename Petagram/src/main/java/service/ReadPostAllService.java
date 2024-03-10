package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ReadPostAllDAO;
import dto.ImageDTO;
import dto.InformDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.NoticeDTO;
import dto.PostDTO;
import dto.ReadPostAllDTO;

@Service
public class ReadPostAllService {
	
	@Autowired
	ReadPostAllDAO dao;
	
	public ReadPostAllDTO getReadPostAll(int page, int pageSize){
		int offset = page * pageSize;
		List<PostDTO> posts = dao.selectPosts(offset, pageSize);
		List<MemberDTO> members = dao.selectMembers();
		
		return new ReadPostAllDTO(posts, members);
	}
	
	public int getTotalPosts() {
		return dao.selectTotalPosts();
	}
	
	public List<LikeDTO> getLikes(int memberid){
		return dao.selectLikes(memberid);
	}
	
	public void updateLike(int memberId, int postId) {
		dao.updateLike(memberId, postId);
	}
	
	public boolean likeExists(int memberId, int postId) {
		boolean result = dao.likeExists(memberId, postId);
		return result;
	}
	
	public void deleteLike(int memberId, int postId) {
		dao.deleteLike(memberId, postId);
	}
	
	public List<LikeDTO> likeTotal(){
		return dao.likeTotal();
	}
	
	public List<ImageDTO> imageTotal(){
		return dao.imageTotal();
	}
	
	/* My Page      */
	public List<PostDTO> getMyPost(int memberid, int postNumber){
		return dao.getMyPost(memberid, postNumber);
	}
	
	public List<PostDTO> getMyAllPost(int memberid){
		return dao.getMyAllPost(memberid);
	}
	
	public List<MemberDTO> memberTotal(){
		return dao.selectMembers();
	}
	
	public int validatePw(int memberid, String password) {
		return dao.validatePw(memberid, password);
	}
	
	public void deleteMember(int memberid) {
		dao.deleteMember(memberid);
	}
	/* My Page      */
	
	public List<InformDTO> getNoticeAll() {
		return dao.selectAllNotice();
	}
	
	public NoticeDTO getAllNoitce() {
		List<InformDTO> informs = dao.selectAllNotice();
		List<MemberDTO> members = dao.selectMembers();
		return new NoticeDTO(members, informs);
	}
	
	public void updateNotice(String title, String textbox) {
		dao.updateNotice(title, textbox);
	}
	
	public void deleteNotice(int informid) {
		dao.deleteNotice(informid);
	}
	
	/* 추가된 내용(notice) */
	public boolean checkAuthority(int memberId) {
		return dao.selectAuthority(memberId);
	}
}
