package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PostDAO;
import dto.ImageDTO1;
import dto.ImageDTO2;
import dto.PostDTO1;
import dto.PostDTO2;
import dto.PostDTO4;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;

	@Override
	public PostDTO2 getPostById(int postId) {
		return postDAO.getPostById(postId);
	}

	@Override
	public int registerPost(PostDTO2 postDTO, int memberId) {
		int postId = postDAO.insertPost(postDTO, memberId);
		return postId;
	}

	@Override
	public void registerImage(String imageName) {
		postDAO.registerImage(imageName);
	}

	@Override
	public void saveImageFilename(int postId, String originalFilename) {
		postDAO.saveImageFilename(postId, originalFilename);
	}

	@Override
	public void selectPostId(PostDTO2 postDTO) {
		postDAO.selectPostId(postDTO);
	}

	@Override
	public void selectLatestPostId(PostDTO2 postDTO) {
		postDAO.selectLatestPostId(postDTO);
	}

	@Override
	public int updateImage(String newFilename, int postId) {
		return postDAO.updateImage(newFilename, postId);
	}

	@Override
	public String getImageFilename(int postId) {
		String filename = postDAO.getImageFilename(postId);

		if (filename == null || filename.isEmpty()) {
			// 결과가 없는 경우
			return "--"; // 빈 문자열 반환
		} else {
			// 결과가 있는 경우
			return filename;
		}
	}

	@Override
	public void uploadImage(ImageDTO2 imageDTO2) {

		// 이미지 파일 이름 가져오기
		String imageName = imageDTO2.getOriginalFilename();

		postDAO.uploadImage(imageName); // DAO에 이미지 업로드를 위임

	}
	
	@Override
	public void updatePost(PostDTO2 postDTO) {
		postDAO.updatePost(postDTO);

	}

	@Override
	public PostDTO4 getPostById4(int postId) {
		return postDAO.getPostById4(postId);

	}
	
	@Override
	public void saveImageFilename1(int postId, String originalFilename) {
		postDAO.saveImageFilename1(postId, originalFilename); 
	}
	
////////
	@Override
	public int deleteoldImages(int postId) {
		return postDAO.deleteoldImages(postId); 
	}

}
