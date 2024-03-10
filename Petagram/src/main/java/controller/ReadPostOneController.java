package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.CommentRq;
import dto.DeletePostRq;
import dto.ImageDTO;
import dto.LikeRq;
import dto.ReadPostOneDTO;
import service.ReadPostOneService;

@Controller
public class ReadPostOneController {

	@Autowired
	ReadPostOneService service;

	/**
	 * 게시물 상세 조회 API
	 * @param postId 게시글 식별번호
	 * @return 게시글 상세 정보
	 * @throws IOException 
	 */
	@GetMapping("/api/post/{postId}")
	@ResponseBody
	public ModelAndView readPostOne(@PathVariable int postId) {
		ReadPostOneDTO readPostOne = service.getReadPostOne(postId);
  		List<ImageDTO> image = service.getImage(postId);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("readPostOne", readPostOne);
				
		if(!image.isEmpty()) {
			mv.addObject("image", image);
		}		
		
		mv.setViewName("ReadPostOne");
		
		return mv;
	}
	
	/**
	 * 댓글 DB 저장
	 * @param request
	 * @return
	 */
	@PostMapping("/api/comment")
	@ResponseBody
	public CommentRq saveComment(@RequestBody CommentRq request) {
		service.insertComment(request.getContent(), request.getMemberId(), request.getPostId());				
		return request;
	}
	
	/**
	 * 댓글 DB 삭제
	 * @param commentId
	 * @return
	 */
	@PostMapping("/api/comment/delete")
	@ResponseBody
	public boolean deleteComment(int commentId) {
		int res = service.deleteComment(commentId);
		
		if(res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 좋아요 클릭 후 조회
	 * @param postId
	 * @param memberId
	 * @return
	 */
	@PostMapping("/api/post/like")
	@ResponseBody
	public int clickLike(@RequestBody LikeRq request) {
		int isClick = service.selectLike(request.getPostId(), request.getMemberId());
		
		if(isClick > 0) {
			service.deleteLike(request.getPostId(), request.getMemberId());
		} else {
			service.insertLike(request.getPostId(), request.getMemberId());
		}
				
		return service.selectLikeAll(request.getPostId());
	}
	
	/**
	 * 게시글 삭제
	 * @param postId
	 * @return
	 */
	@PostMapping("/api/post/delete")
	@ResponseBody
	public int deletePost(@RequestBody DeletePostRq request) {
		return service.deletePost(request.getPostId());
	}
	
}
