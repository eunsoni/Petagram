package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.HospitalDTO;

@Repository
public class HospitalDAO {

	@Autowired
	SqlSession session;
	
	/**
	 * DB 데이터 유무
	 * @return
	 */
	public int selectAllCnt() {
		return session.selectOne("selectAllCnt");
	}
	
	/**
	 * API 응답 DB 저장
	 * @param list
	 */
	public void insertHospitalInfo(List<HospitalDTO> list) {
		for (HospitalDTO one : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hospitalName", one.getHospitalName());
			map.put("localAddr", one.getLocalAddr());
			map.put("roadAddr", one.getRoadAddr());
			map.put("longitude", one.getLongitude());
			map.put("latitude", one.getLatitude());
		
			if((int)session.selectOne("selectDupl", map) == 0) {
				session.insert("insertHospitalInfo", map);
			}
		}
	}

	/**
	 * 검색주소를 포함하는 병원정보 조회
	 * @param address
	 */
	public List<HospitalDTO> selectByAddr(String address) {
		return session.selectList("selectByAddr", address);
	}
	
}
