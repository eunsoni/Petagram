package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.ImageDTO;
import dto.InformDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.PostDTO;

@Repository
public class ReadPostAllDAO {

	@Autowired
	SqlSession session;

	public List<PostDTO> selectPosts(int offset, int limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("offset", offset);
		params.put("pageSize", limit);
		return session.selectList("selectPosts", params);
	}

	public List<MemberDTO> selectMembers() {
		return session.selectList("selectMembers", session);
	}

	public int selectTotalPosts() {
		return session.selectOne("selectTotal", session);
	}

	public List<LikeDTO> selectLikes(int memberid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberid);
		return session.selectList("selectLikes", params);
	}

	public void updateLike(int memberId, int postId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("postId", postId);
		session.update("updateLikes", params);
	}

	public boolean likeExists(int memberId, int postId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("postId", postId);

		int yes = session.selectOne("likeExists", params);
		if (yes > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteLike(int memberId, int postId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("postId", postId);

		session.delete("deleteLikes", params);
	}

	public List<LikeDTO> likeTotal() {
		List<LikeDTO> likeTotal = session.selectList("likeTotal", session);
		return likeTotal;
	}

	public List<ImageDTO> imageTotal() {
		List<ImageDTO> imageTotal = session.selectList("imageTotal", session);
		return imageTotal;
	}

	/*                 */
	public List<PostDTO> getMyPost(int memberid, int postNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberid);
		params.put("postNumber", postNumber);
		List<PostDTO> myPosts = session.selectList("getMyPost", params);
		return myPosts;
	}

	public List<PostDTO> getMyAllPost(int memberid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberid);
		List<PostDTO> myPosts = session.selectList("getMyAllPost", params);
		return myPosts;
	}

	public int validatePw(int memberid, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberid);
		params.put("password", password);
		int myPosts = session.selectOne("validatePw", params);
		return myPosts;
	}

	public void deleteMember(int memberid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberid);
		session.delete("deleteMember", params);
	}
	
	public List<InformDTO> selectAllNotice() {
		return session.selectList("selectAllNotice", session);
	}

	public void updateNotice(String title, String textbox) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("textbox", textbox);
		session.update("updateNotice", params);
	}
	
	public void deleteNotice(int informid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("informId", informid);
		session.delete("deleteNotice", params);
	}
	
	/* 추가된 내용(notice) */
	public boolean selectAuthority(int memberId) {
		return session.selectOne("selectAuthority", memberId);
	}
}
