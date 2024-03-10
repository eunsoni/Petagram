package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.ImageDTO2;
import dto.PostDTO1;
import dto.PostDTO2;
import dto.PostDTO4;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int registerPost(PostDTO2 postDTO) { //
		int insertrow = sqlSession.insert("registerPost", postDTO);
		return insertrow;
	}

	@Override
	public int insertPost(PostDTO2 postDTO, int memberId) { //
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("title", postDTO.getTitle());
		map.put("content", postDTO.getContent());
		map.put("memberId", memberId);

		sqlSession.insert("insertPost", map);

		return Integer.valueOf(String.valueOf(map.get("postId")));
	}

	@Override
	public PostDTO2 getPostById(int postId) {
		return sqlSession.selectOne("getPostById", postId);
	}

	@Override
	public int updatePost(PostDTO2 postDTO) {
		return sqlSession.update("updatePost", postDTO);
	}

	@Override
	public int registerImage(String imageName) {
		return sqlSession.insert("insertImage", imageName);
	}

	@Override
	public int saveImageFilename(int postId, String originalFilename) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("postId", postId);
		map.put("src", originalFilename);

		return sqlSession.insert("insertImage", map);
	}

	@Override
	public int selectPostId(PostDTO2 postDTO) { //
		return sqlSession.selectOne("selectPostId", postDTO);
	}

	@Override
	public int selectLatestPostId(PostDTO2 postDTO) {
		return sqlSession.selectOne("selectLatestPostId", postDTO);

	}

	@Override
	public int updateImage(String src, int postId) {
		Map<String, Object> map = new HashMap<>();
		map.put("src", src);
		map.put("postId", postId);
		return sqlSession.update("UpdateImage", map);
	}

	@Override
	public String getImageFilename(int postId) {
		return sqlSession.selectOne("getImageFilename", postId);
	}

	@Override
	public void uploadImage(String imageName) {
		sqlSession.insert("uploadImage", imageName);
	}

	@Override
	public PostDTO4 getPostById4(int postId) {
		return sqlSession.selectOne("getPostById4", postId);
	}

	@Override
	public int saveImageFilename1(int postId, String originalFilename) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("postId", postId);
		map.put("src", originalFilename);

		return sqlSession.insert("saveImageFilename1", map);
	}

///////////
	@Override
	public int deleteoldImages(int postId) {
		return sqlSession.delete("deleteoldImages", postId);
	}

}
