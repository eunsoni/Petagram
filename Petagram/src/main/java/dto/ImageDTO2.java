package dto;

import org.springframework.stereotype.Component;

@Component
public class ImageDTO2 {

	private String originalFilename;

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public Object getImageFile() {
		// TODO Auto-generated method stub
		return originalFilename;
	}

}