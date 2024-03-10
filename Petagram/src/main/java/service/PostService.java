package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.ImageDTO1;
import dto.ImageDTO2;
import dto.PostDTO1;
import dto.PostDTO2;
import dto.PostDTO4;
@Service
public interface PostService {

	int registerPost(PostDTO2 postDTO, int memberId);
	
    void updatePost(PostDTO2 postDTO);

    PostDTO2 getPostById(int postId);

	void registerImage(String imageName);

	void saveImageFilename(int postId, String originalFilename);

	void selectPostId(PostDTO2 postDTO); //

	void selectLatestPostId(PostDTO2 postDTO);

	int updateImage(String newFilename, int postId);

	String getImageFilename(int postId);

	void uploadImage(ImageDTO2 imageDTO2);

	PostDTO4 getPostById4(int postId);
	
	void saveImageFilename1(int postId, String originalFilename);
	
///////
	int deleteoldImages(int postId);
}



