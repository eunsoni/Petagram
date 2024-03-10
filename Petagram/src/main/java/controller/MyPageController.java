package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ReadPostAllDAO;
import dto.ImageDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.PostDTO;
import service.ReadPostAllService;

@Controller
public class MyPageController {
	@Autowired
	ReadPostAllService service;
	
	@GetMapping("/api/mypage")
	public String myPage(Model model, HttpSession session) {
		Integer memberIdObj = (Integer) session.getAttribute("memberid");
		int memberid = (memberIdObj != null) ? memberIdObj : 0; 
				
		List<LikeDTO> likes = service.getLikes(memberid);
		List<LikeDTO> likeTotal = service.likeTotal();
		List<ImageDTO> images = service.imageTotal();
		int postNumber = 3 ;
		List<PostDTO> myPosts = service.getMyPost(memberid, postNumber);
		List<MemberDTO> memberTotal = service.memberTotal();
		
		model.addAttribute("likes", likes);
		model.addAttribute("likeTotal", likeTotal);
		model.addAttribute("memberTotal", memberTotal);
		model.addAttribute("images", images);
		model.addAttribute("myPosts", myPosts);
		model.addAttribute("session", session);
		
		return "MyPage";
	}
}
