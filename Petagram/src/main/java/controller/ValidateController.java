package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.ReadPostAllService;

@Controller
public class ValidateController {
	
	@Autowired
	ReadPostAllService service;
	
	@GetMapping("api/validate")
	public ResponseEntity<String> validatePassword(HttpSession session, @RequestParam String password) {
		Integer memberIdObj = (Integer) session.getAttribute("memberid");
		int memberid = (memberIdObj != null) ? memberIdObj : 0; 
		
		int likeTotal = service.validatePw(memberid, password);
		
		if(likeTotal > 0) {
			return ResponseEntity.ok("Validation successful");
		} else {
			return ResponseEntity.badRequest().body("Validation failed");
		}
	}
}