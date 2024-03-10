package dto;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class HospitalDTO {
	//@JsonProperty("BIZPLC_NM")
	private String hospitalName;
	
	//@JsonProperty("REFINE_LOTNO_ADDR")
	private String localAddr;
	
	//@JsonProperty("REFINE_ROADNM_ADDR")
	private String roadAddr;
	
	//@JsonProperty("REFINE_WGS84_LOGT")
	private Double longitude;
	
	//@JsonProperty("REFINE_WGS84_LAT")
	private Double latitude;

	public HospitalDTO() {
		super();
	}

	public HospitalDTO(String hospitalName, String localAddr, String roadAddr, Double longitude, Double latitude) {
		super();
		this.hospitalName = hospitalName;
		this.localAddr = localAddr;
		this.roadAddr = roadAddr;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getLocalAddr() {
		return localAddr;
	}

	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	public String getRoadAddr() {
		return roadAddr;
	}

	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
