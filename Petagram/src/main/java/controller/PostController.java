package controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.ImageDTO1;
import dto.PostDTO1;
import service.PostService;
import dto.PostDTO2;
import dto.ImageDTO2;

@Controller
@RequestMapping("/api/post")
public class PostController {
	String originalFilename;

	@Autowired
	private PostService postService;

	@GetMapping("/write")
	public String showWritePage() {
		return "CreatePost";
	}

	@PostMapping("/write")
	public String writeProcess(HttpSession session, @ModelAttribute("postDTO") PostDTO2 postDTO,
			@ModelAttribute("imageDTO") ImageDTO1 imageDTO) throws Exception {

		int memberId = (int) session.getAttribute("memberid");

		// Save post details
		int postId = postService.registerPost(postDTO, memberId);

		// Save images
		String savePath = "c:/image/";
		String filename = null, newFilename = null;

		MultipartFile imageFile = imageDTO.getImageFile();
		originalFilename = imageFile.getOriginalFilename();

		if (imageFile != null && !imageFile.isEmpty()) {
			filename = imageFile.getOriginalFilename();
			String beforeExt = filename.substring(0, filename.indexOf("."));
			String ext = filename.substring(filename.indexOf("."));
			newFilename = beforeExt + "(" + UUID.randomUUID().toString() + ")" + ext;

			File saveFile = new File(savePath + newFilename);
			imageFile.transferTo(saveFile);
		}

		if (!imageDTO.getImageFile().getOriginalFilename().isEmpty()) {
			postService.saveImageFilename(postId, originalFilename);
		}

		return "redirect:/api/home";
	}

	@GetMapping("/edit/{postId}")
	public String showEditPage(@PathVariable int postId, Model model) {

		PostDTO2 postDTO = postService.getPostById(postId);
		String imageFilename = postService.getImageFilename(postId); // 이미지 파일 이름 가져오기

		String title = postDTO.getTitle(); // 게시글 제목 가져오기
		String content = postDTO.getContent(); // 게시글 내용 가져오기

		model.addAttribute("detaildto", postDTO);
		System.out.println("수정창: 게시글 가져오기 중 postDTO" + postDTO);

		model.addAttribute("imageFilename", imageFilename);
		System.out.println("수정창: 게시글 가져오기 중 imageFilename" + imageFilename);

		model.addAttribute("title", title); // 모델에 게시글 제목 추가
		model.addAttribute("content", content); // 모델에 게시글 내용 추가

		return "UpdatePost";
	}

	@PostMapping("/edit/{postId}")
	public String editPost(@PathVariable int postId, @ModelAttribute("PostDTO2") PostDTO2 postDTO,
			@ModelAttribute("imageDTO") ImageDTO1 imageDTO) throws Exception {

		postService.updatePost(postDTO);
		System.out.println("00게시글업데이트:postDTO" + postDTO);

		// Save images
		String savePath = "c:/image/";
		String filename = null, newFilename = null;

		////// 기존 이미지 삭제
		postService.deleteoldImages(postDTO.getPostId());

		MultipartFile imageFile = imageDTO.getImageFile();
		originalFilename = imageFile.getOriginalFilename();

		if (imageFile != null && !imageFile.isEmpty()) {
			filename = imageFile.getOriginalFilename();
			String beforeExt = filename.substring(0, filename.indexOf("."));
			String ext = filename.substring(filename.indexOf("."));
			newFilename = beforeExt + "(" + UUID.randomUUID().toString() + ")" + ext;

			File saveFile = new File(savePath + newFilename);
			imageFile.transferTo(saveFile);
		}

		if (!imageDTO.getImageFile().getOriginalFilename().isEmpty()) {
			postService.saveImageFilename(postDTO.getPostId(), originalFilename);
		}

		return "redirect:/api/post/{postId}"; // 해당 게시글 상세창으로 이동
	}

}
