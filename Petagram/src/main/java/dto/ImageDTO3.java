package dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ImageDTO3{

	    private MultipartFile imageFile;
	    private int memberid;
	  

		public int getMemberid() {
			return memberid;
		}

		public void setMemberid(int memberid) {
			this.memberid = memberid;
		}

		public MultipartFile getImageFile() {
	        return imageFile;
	    }

	    public void setImageFile(MultipartFile imageFile) {
	        this.imageFile = imageFile;
	    }
	    
	}
