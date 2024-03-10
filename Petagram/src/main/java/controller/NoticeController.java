package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.InformDTO;
import service.ReadPostAllService;

@Controller
public class NoticeController {

	@Autowired
	ReadPostAllService service;
	
	@GetMapping("/api/notice")
	public String inform(Model model, HttpSession session, 
			@RequestParam(required = false) Integer memberId, @RequestParam(required = false) String textTitle, @RequestParam(required = false) String text) {
		
		memberId = (Integer)session.getAttribute("memberid");
		boolean authority = false;
		
		if(textTitle != null && text != null) {
			service.updateNotice(textTitle, text);
		}
		
		/* 추가된 내용 */
		authority = service.checkAuthority(memberId);
		/*----------*/
		
		List<InformDTO> informs = service.getNoticeAll();
		model.addAttribute("informs", informs);
		model.addAttribute("memberId", memberId);
		/* 추가된 내용 */
		model.addAttribute("authority", authority);
		/*----------*/
		return "notice";
	}
}
