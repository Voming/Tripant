package mclass.store.tripant.diary.controller;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.google.gson.Gson;





@Controller
public class ImageUploadController {
	@Value("${cloudinary.cloud_name}")
	private String cloudName;
	@Value("${cloudinary.api_key}")
	private String apiKey;
	@Value("${cloudinary.api_secret}")
	private String apiSecret;

	@PostMapping("/post/cloudinary")
	@ResponseBody
	public String postCloudinary(MultipartHttpServletRequest multiFile) throws IOException {
		
		
		System.out.println("들어옴");
		// 내 클라우드 정보로 cloudinary 객체 생성 
		// Cloudinary 설정
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret,
				"secure", true)
		);
		
		//		String uploadPath = request.getServletContext().getRealPath("/resources/uploadfile");
		String uploadPath = cloudinary.uploader().getUploadUrl(ObjectUtils.emptyMap());
		System.out.println("uploadPath : " + uploadPath);
		
		File uploadPathFile = new File(uploadPath);
		if(!uploadPathFile.exists()) {
			uploadPathFile.mkdirs();
		}

		// 업로드된 파일 가져오기
		MultipartFile file = multiFile.getFile("upload");
		if(file != null) {
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if(file.getContentType().toLowerCase().startsWith("image/")) { //이미지 파일만 검색
					try {
						String originalFileName = file.getOriginalFilename();
						// 임시 파일 생성 및 전송
						File f = Files.createTempFile("temp",file.getOriginalFilename()).toFile();
						file.transferTo(f);
					     // Cloudinary에 이미지 업로드
						
						Map<String, String> config = new HashMap<String, String>();
						config.put("cloud_name", cloudName);
						config.put("api_key", apiKey);
						config.put("api_secret", apiSecret);
//						"http://res.cloudinary.com/dnhmep72p/image/upload/v1719391588/a4rpapbglkqd1g7lxipk.png
					
						Map<String, String> uploadResult = cloudinary.uploader().upload(f, ObjectUtils.emptyMap());

						System.out.println("==============================================");
						System.out.println(uploadResult.get("url"));
;
						 // 업로드 성공 시 JSON 응답 생성
						HashMap<String, String> map = new HashMap<String, String>();
						
						map.put("uploaded", "1");
						map.put("fileName", originalFileName);
						map.put("url", uploadResult.get("url").toString());
						return new Gson().toJson(map);
					} catch(Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
		return "failur";
	}
}
