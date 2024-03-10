package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ImageDTO;
import dto.LikeDTO;
import dto.MemberDTO;
import dto.PostDTO;
import dto.ReadPostAllDTO;
import service.ReadPostAllService;

@Controller
public class MyAllController {

	@Autowired
	ReadPostAllService service;

	@GetMapping("api/myall")
	public String readPostAll(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session) {

		Integer memberIdObj = (Integer) session.getAttribute("memberid");
		int memberid = (memberIdObj != null) ? memberIdObj : 0; 


		List<LikeDTO> likes = service.getLikes(memberid);
		List<LikeDTO> likeTotal = service.likeTotal();
		List<ImageDTO> images = service.imageTotal();
		int pageSize = 8;
		List<PostDTO> myPosts = service.getMyAllPost(memberid);
		int totalPosts = myPosts.size();
		ReadPostAllDTO all = service.getReadPostAll(page, pageSize);
		List<MemberDTO> memberTotal = service.memberTotal();
		
		
		model.addAttribute("memberTotal", memberTotal);
		model.addAttribute("all", all);
		model.addAttribute("images", images);
		model.addAttribute("likes", likes);
		model.addAttribute("likeTotal", likeTotal);
		model.addAttribute("myPosts", myPosts);
		model.addAttribute("session", session);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", Math.ceil(totalPosts / (double) pageSize));
		
		return "MyAllPage";
	}

}
/* List<LikeDTO> likes = service.getLikes(); */
/* model.addAttribute("likes",likes); */