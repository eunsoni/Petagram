package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dto.HospitalDTO;
import service.HospitalService;

@Controller
public class HospitalController {
	
	@Autowired
	HospitalService service;
	
	/**
	 * 동물병원 초기화면
	 * @return
	 */
	@RequestMapping("/api/hospital")
	public String start() {
		return "HospitalInfo";
	}
	
	/**
	 * 검색주소를 포함하는 병원정보 조회
	 * @param address
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@PostMapping("/api/hospital/search")
	@ResponseBody
	public List<HospitalDTO> getHospitalInfo(String address) throws JsonMappingException, JsonProcessingException{		
		return service.getHospitalInfoByAddr(address);
	}
}
