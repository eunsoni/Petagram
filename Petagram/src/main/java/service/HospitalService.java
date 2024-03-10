package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.HospitalDAO;
import dto.HospitalDTO;

@Service
public class HospitalService {
	
	@Autowired
	HospitalDAO dao;
	
	@Value("${hospital.openapi.service-key}")
	private String serviceKey;
	
	/**
	 * 요청 URL 및 쿼리스트링 설정
	 * @return
	 */
	private String buildUri(int index) {
		UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("openapi.gg.go.kr")
                .path("Animalhosptl")
                .queryParam("Key", serviceKey)
                .queryParam("Type", "json")
                .queryParam("pIndex", index)
                .queryParam("pSize", 1000)
                .build();
		return uri.toUriString();
	}
	
	/**
	 * 오픈 API 응답 객체
	 * @return List<HospitalDTO>
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	public List<HospitalDTO> getAPIReponse() throws JsonMappingException, JsonProcessingException {
		
		String uri;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		List<HospitalDTO> list = new ArrayList<HospitalDTO>();
		
		for (int idx = 1; idx < 4; idx++) {
			uri = this.buildUri(idx);
			
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
			
			ObjectMapper mapper = new ObjectMapper();
						
	        JsonNode jsonNode = mapper.readValue(result.getBody(), JsonNode.class);
	        
	        Object[] hospitalName = (Object[]) jsonNode.findValues("BIZPLC_NM").toArray();
	        Object[] localAddr = (Object[]) jsonNode.findValues("REFINE_LOTNO_ADDR").toArray();
	        Object[] roadAddr = (Object[]) jsonNode.findValues("REFINE_ROADNM_ADDR").toArray();
	        Object[] longitude = (Object[]) jsonNode.findValues("REFINE_WGS84_LOGT").toArray();
	        Object[] latitude = (Object[]) jsonNode.findValues("REFINE_WGS84_LAT").toArray();

	        for (int i = 0; i < hospitalName.length; i++) {
	        	String longitudeStr = longitude[i].toString().replaceAll("[\\[\\]\"]", "");
	        	String latitudeStr = latitude[i].toString().replaceAll("[\\[\\]\"]", "");

	        	double longitudeDouble;
	        	double latitudeDouble;
	        	
	        	try {
	        		longitudeDouble = Double.parseDouble(longitudeStr);
	        	} catch (NumberFormatException e) {
	        		longitudeDouble = 0;
	        	}
	        	
	        	try {
	        		latitudeDouble = Double.parseDouble(latitudeStr);
	        	} catch (NumberFormatException e) {
	        		latitudeDouble = 0;
	        	}
	        	
	        	HospitalDTO dto = new HospitalDTO(
	        			hospitalName[i].toString().replaceAll("[\\[\\]\"]", ""),
	        			localAddr[i].toString().replaceAll("[\\[\\]\"]", ""),
	        			roadAddr[i].toString().replaceAll("[\\[\\]\"]", ""),
	        			longitudeDouble,
	        			latitudeDouble
			    		);

			    list.add(dto);
			}
		}		
        return list;
	}
	
	/**
	 * 검색주소를 포함하는 병원정보 조회
	 * @param address
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public List<HospitalDTO> getHospitalInfoByAddr(String address) throws JsonMappingException, JsonProcessingException{
		if(dao.selectAllCnt() == 0) {
			List<HospitalDTO> list = getAPIReponse();
			dao.insertHospitalInfo(list);	
		}
		
		List<HospitalDTO> result = dao.selectByAddr(address);
		
		return result;
	}
	
}
