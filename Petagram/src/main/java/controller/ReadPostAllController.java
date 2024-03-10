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
import dto.ReadPostAllDTO;
import service.ReadPostAllService;

@Controller
public class ReadPostAllController {

	@Autowired
	ReadPostAllService service;

	@GetMapping("/api/post")
	public String readPostAll(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session,
			@RequestParam(required = false) Integer postId, @RequestParam(required = false) Integer memberId) {
		Integer memberIdObj = (Integer) session.getAttribute("memberid");
		int memberid = (memberIdObj != null) ? memberIdObj : 0; 

		if (memberid != 0) {
			if (postId != null && memberId != null) {
				boolean result = service.likeExists(memberId, postId);

				if (result) {
					service.deleteLike(memberId, postId);

				} else {
					service.updateLike(memberId, postId);

				}
			} else {
				// �ƹ��͵� ������� ����
			}

			List<LikeDTO> likes = service.getLikes(memberid);
			List<LikeDTO> likeTotal = service.likeTotal();
			List<ImageDTO> images = service.imageTotal();
			int pageSize = 8;
			int totalPosts = service.getTotalPosts();
			ReadPostAllDTO all = service.getReadPostAll(page, pageSize);
			
			model.addAttribute("images", images);
			model.addAttribute("likes", likes);
			model.addAttribute("likeTotal", likeTotal);
			model.addAttribute("all", all);
			model.addAttribute("session", session);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", Math.ceil(totalPosts / (double) pageSize));
		} else if (memberid == 0) {
			if (memberId != null || postId != null) {
				return "redirect:/other";
			} else {
				List<LikeDTO> likeTotal = service.likeTotal();
				int pageSize = 8;
				int totalPosts = service.getTotalPosts();
				ReadPostAllDTO all = service.getReadPostAll(page, pageSize);
				List<ImageDTO> images = service.imageTotal();
				
				model.addAttribute("images", images);
				model.addAttribute("likeTotal", likeTotal);
				model.addAttribute("all", all);
				model.addAttribute("session", session);
				model.addAttribute("currentPage", page);
				model.addAttribute("totalPages", Math.ceil(totalPosts / (double) pageSize));
			}
		}
		return "ReadPostAll";
	}

}
/* List<LikeDTO> likes = service.getLikes(); */
/* model.addAttribute("likes",likes); */