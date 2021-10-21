package coms309.vb6.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import coms309.vb6.model.Profile;
import coms309.vb6.model.ProfileImage;
import coms309.vb6.model.StoryImage;
import coms309.vb6.repository.ProfileImageRepository;
import coms309.vb6.repository.ProfileRepository;
import coms309.vb6.repository.UserRepository;

@RestController
public class ProfileImageController {

	  private String failure = "{\"message\":\"failure\"}";
	  private String success = "{\"message\":\"success\"}";	
	  private static String uploadPath = "/profileImage/";
	  @Autowired
	  HttpServletRequest httpServletRequest;
	  @Autowired
	  ProfileRepository profileRepository; 
	  @Autowired
	  ProfileImageRepository profileImageRepository; 

	  
	 @PostMapping(path = "/profiles/{id}/image")
	  String createProfileImage(@PathVariable int id, @RequestParam("image") MultipartFile imageFile) throws IllegalStateException, IOException, FileNotFoundException{
	      if(imageFile.isEmpty()) 
	          return failure;      
		  String fullPath = httpServletRequest.getServletContext().getRealPath(uploadPath + id +"/");
	      ProfileImage profileImage = new ProfileImage(fullPath);
	      Profile profile = profileRepository.findById(id);
	      if(profile == null)
	      {
	    	  return failure;
	      }
	      profileImage.setProfile(profile); 
	      profileImageRepository.save(profileImage);
	      fullPath+=(profileImage.getId()+"_"+imageFile.getOriginalFilename());
	      System.out.println(fullPath);
	      if (!new File(fullPath).exists())
	      {
	         System.out.println(new File(fullPath).mkdirs());
	      }
	      profileImage.setImagePath(fullPath);
	      profileImageRepository.save(profileImage);
	      
	      
	      profileRepository.save(profile);
	      File tempFile = new File(fullPath);
	      imageFile.transferTo(tempFile); 
	      return "{\"message\":\"" + profileImage.getId()+ "\"}";
	  }
	 @DeleteMapping(path = "/profiles/{id}/image/{image_id}")
	 String deleteProfileImage(@PathVariable int id,@PathVariable int image_id,@RequestParam("image") MultipartFile imageFile)throws IllegalStateException, IOException, FileNotFoundException {
		 if(imageFile.isEmpty()) 
	          return failure;  


		 
		 
		 
		 
		 return failure;
		 
	 }
	 
	  @PostMapping(path="/profileImage/{image_id}")
	  public String getImagePath(@PathVariable int image_id)
	  {
		  return profileImageRepository.findById(image_id).getImagePath();
	  }
	  
}
