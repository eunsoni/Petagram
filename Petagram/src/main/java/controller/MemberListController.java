package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dto.MemberDTO;
import dto.PetDTO;
import service.MemberService;

@Controller
public class MemberListController {
	// service(memberList메서드)-dao(memberList메소드)-sql(memberList메소드)-sql매핑
	// id(memberlist)

	@Autowired
	MemberService service;

//	=============================loginmember====================================
	@RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "loginmember"; 
    }
	 
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public String loginmember(HttpServletRequest request, HttpSession session, Model model) {
        session.setAttribute("isFromSession", true);
        String id = request.getParameter("inputId");
        String pw = request.getParameter("inputPass");
        
        Integer result = service.memberExists(id, pw);
    	System.out.println("1:" + result);

        if(result == null || result <= 0) {
        	String msg = (String)service.loginMember(id, pw);
        	model.addAttribute("loginMessage", msg);
        	return "loginmember";
        }else if(result != null && result > 0) {
        	int memberid = service.getMemberid(id);
        	session.setAttribute("memberid", memberid);
        	System.out.println("4: " + session.getAttribute("memberid"));
        	return "redirect:/api/home";
        }

        return "loginmember";
    }

//	========================registermember=============================
	@RequestMapping(value = "/api/registermember", method = RequestMethod.GET)
	public String registerMember() {
		return "registermember";
	}

	@PostMapping("/api/registermember")
	public String registerMemberResult(@ModelAttribute MemberDTO dto, @ModelAttribute PetDTO pet, Model model) {
		// 회원 정보 등록
		String result = service.registerMember(dto);

		if (result.equals("아이디 중복")) {
			model.addAttribute("registerMessage", result);
			return "redirect:/registermember";
		} else if (result.equals("이메일 중복")) {
			model.addAttribute("registerMessage", result);
			return "redirect:/registermember";
		} else if (result.equals("전화번호 중복")) {
			model.addAttribute("registerMessage", result);
			return "redirect:/registermember";
		} else if (result.equals("회원 등록 완료")) {
			// 애완동물 정보 등록
			service.registerPet(pet);
			return "loginmember"; // 회원 목록 페이지로 이동

		} else {
			model.addAttribute("registerMessage", "비밀번호가 깁니다. 다시 입력해보세요.");
			return "redirect:/registermember";
		}
	}

//	=================================updatemember==========================
	@GetMapping("/api/updatemember")
	public String showUpdateMemberPage(Model model, HttpSession session) {
		int memberid = (int) session.getAttribute("memberid");
		MemberDTO dto = service.settingDto(memberid);
		PetDTO pet = service.settingPet(memberid);

		model.addAttribute("membername", dto.getMemberName());
		model.addAttribute("email", dto.getEmail());
		model.addAttribute("phone", dto.getPhone());
		model.addAttribute("petname", pet.getPetname());
		model.addAttribute("age", pet.getAge());
		model.addAttribute("pettype", pet.getPettype());
		model.addAttribute("gender", pet.getGender());

		return "updatemember";
	}

	@PostMapping("/api/updatemember")
	public String updateMember(HttpServletRequest request, HttpSession session, @ModelAttribute MemberDTO dto,
			@ModelAttribute PetDTO pet) {
		// Update member information
		System.out.println(request.getParameter("membername"));
		System.out.println(request.getParameter("phone"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("petname"));
		System.out.println(request.getParameter("age"));
		System.out.println(request.getParameter("pettype"));
		System.out.println(request.getParameter("gender"));

		String membername = request.getParameter("membername");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String petname = request.getParameter("petname");
		int age = Integer.parseInt(request.getParameter("age"));
		int memberid = Integer.parseInt(String.valueOf(session.getAttribute("memberid")));

		String pettype = request.getParameter("pettype");
		String gender = request.getParameter("gender");

		MemberDTO settingdto = new MemberDTO();
		PetDTO settingpet = new PetDTO();
		settingdto.setMemberName(membername);
		settingdto.setPhone(phone);
		settingdto.setEmail(email);
		settingdto.setMemberId(memberid);

		settingpet.setPetname(petname);
		settingpet.setAge(age);
		settingpet.setPettype(pettype);
		settingpet.setGender(gender);
		settingpet.setMemberid(memberid);

		service.updateMember(settingdto);
		service.updatePet(settingpet);

		return "redirect:/api/mypage";
	}

}
