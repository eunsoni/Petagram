package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.ImageDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.PostDTO;

@Repository
public class HomeDAO {
	
	@Autowired
	SqlSession session; 
	
	public List<PostDTO> selectPosts(int offset, int limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("offset", offset);
		params.put("pageSize", limit);
		return session.selectList("selectPostsHome", params); 
	}
	
	public List<Integer> selectPopPosts() {
		return session.selectList("selectPopPosts", session);
	}
	
	public List<PostDTO> selectPopPosts2(List<Integer> id) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		if(id.size() != 0) {
			params.put("id1", id.get(0));
			params.put("id2", id.get(1));
			params.put("id3", id.get(2));
			params.put("id4", id.get(3));
			params.put("id5", id.get(4));
		}
		return session.selectList("selectPopPosts2", params);
	}
	
	public List<MemberDTO> selectMembers(){
		return session.selectList("selectMembers", session);
	}
	
	public int selectTotalPosts(){
		return session.selectOne("selectTotalPosts", session);
	}
	
	public List<LikeDTO> selectLikes(int memberid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberid);
		return session.selectList("selectLikes", params);
	}
	
	public void updateLike(int memberId, int postId) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("memberId", memberId);
	    params.put("postId", postId);
	    session.update("updateLike", params);
	}
	
	public boolean likeExists(int memberId, int postId) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("memberId", memberId);
	    params.put("postId", postId);
	    
		int yes = session.selectOne("likeExists", params);
		if(yes>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteLike(int memberId, int postId) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("memberId", memberId);
	    params.put("postId", postId);
	    
	    session.delete("deleteLike", params);
	}
	
	public List<LikeDTO> likeTotal(){
		List<LikeDTO> likeTotal = session.selectList("likeTotal", session);
		return likeTotal;
	}
	
	public List<ImageDTO> imageTotal(){
		List<ImageDTO> imageTotal = session.selectList("imageTotal", session);
		return imageTotal;
	}
	
	public String imageOne(int postId) {
		return session.selectOne("imageOne", session);
	}
	
	
	
	

}
