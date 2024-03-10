package dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ImageDTO4{

	    private MultipartFile imageFile;

	    public MultipartFile getImageFile() {
	        return imageFile;
	    }

	    public void setImageFile(MultipartFile imageFile) {
	        this.imageFile = imageFile;
	    }
	}
