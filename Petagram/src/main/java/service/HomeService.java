package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HomeDAO;
import dto.ImageDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.PostDTO;
import dto.HomeDTO;

@Service
public class HomeService {
	
	@Autowired
	HomeDAO dao;
	
	public HomeDTO getHome(int page, int pageSize) {
		int offset = page * pageSize;
		List<PostDTO> posts = dao.selectPosts(offset, pageSize);
		List<MemberDTO> members = dao.selectMembers();
		
		return new HomeDTO(posts, members);
	}
	
	public HomeDTO getPopPosts(List<Integer> id) { 
		List<PostDTO> posts = dao.selectPopPosts2(id); 
		List<MemberDTO> members = dao.selectMembers();
		
		return new HomeDTO(posts, members);
	}
	
	public List<Integer> getid() {
		return dao.selectPopPosts();
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
	
	public List<ImageDTO> imageTotal( ){
		return dao.imageTotal(); 
	}
	
	public String imageOne(int postId) {
		return dao.imageOne(postId);
	}
	
}
