package dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import dto.ImageDTO2;
import dto.PostDTO1;
import dto.PostDTO2;
import dto.PostDTO4;

@Repository
public interface PostDAO {

	int updatePost(PostDTO2 postDTO);

	PostDTO2 getPostById(int postId);

	int insertPost(PostDTO2 postDTO, int memberId);

	int registerPost(PostDTO2 postDTO);

	int registerImage(String imageName);

	int saveImageFilename(int postId, String originalFilename);

	int selectPostId(PostDTO2 postDTO); //

	int selectLatestPostId(PostDTO2 postDTO);

	int updateImage(String newFilename, int postId);

	String getImageFilename(int postId);

	void uploadImage(String imageName);

	PostDTO4 getPostById4(int postId);

	int saveImageFilename1(int postId, String originalFilename);

/////////
	int deleteoldImages(int postId);
}
