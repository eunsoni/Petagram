package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.HomeDTO;
import dto.ImageDTO;
import dto.LikeDTO;
import service.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	HomeService service;
	
	@GetMapping("/api/home")
	public String home(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session,
			@RequestParam(required = false) Integer postId, @RequestParam(required = false) Integer memberId) {
		
		Integer memberIdObj = (Integer) session.getAttribute("memberid");
		int memberid = (memberIdObj != null) ? memberIdObj : 0;
		
		if(memberid != 0) {
			if(postId != null && memberId != null) {
				boolean result = service.likeExists(memberId, postId);
				
				if (result) {
					service.deleteLike(memberId, postId);
				}
				else {
					service.updateLike(memberId, postId);
				}
			}
			
			List<LikeDTO> likes = service.getLikes(memberid);
			List<LikeDTO> likeTotal = service.likeTotal();
			List<ImageDTO> images = service.imageTotal();
			int pageSize = 15;
			int totalPosts = service.getTotalPosts();
			HomeDTO home = service.getHome(page, pageSize);
			List<Integer> id = service.getid();
			HomeDTO pophome = service.getPopPosts(id); 
			
			model.addAttribute("images", images); //jsp - item
			model.addAttribute("likes", likes);
			model.addAttribute("likeTotal", likeTotal); 
			model.addAttribute("home", home);
			model.addAttribute("pophome", pophome);
			model.addAttribute("session", session);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", Math.ceil(totalPosts / (double) pageSize)); 
		}
		else if (memberid == 0) { 
			if (memberId != null || postId != null) {
				return "redirect:/other";
			}
		}
		else {
			List<LikeDTO> likeTotal = service.likeTotal();
			int pageSize = 15;
			int totalPosts = service.getTotalPosts();
			HomeDTO home = service.getHome(page, pageSize);
			List<Integer> id = service.getid();
			HomeDTO pophome = service.getPopPosts(id); 
			List<ImageDTO> images = service.imageTotal();
			
			model.addAttribute("images", images);
			model.addAttribute("likeTotal", likeTotal);
			model.addAttribute("home", home);
			model.addAttribute("pophome", pophome);
			model.addAttribute("session", session);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", Math.ceil(totalPosts / (double) pageSize));
		}
		
		return "home";
	}
	
	
	
}
