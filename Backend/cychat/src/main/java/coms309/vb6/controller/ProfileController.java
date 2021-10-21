package coms309.vb6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coms309.vb6.model.Profile;
import coms309.vb6.model.User;
import coms309.vb6.repository.ProfileRepository;
import coms309.vb6.repository.UserRepository;




@RestController
public class ProfileController {
	  @Autowired
	  ProfileRepository profileRepository; 
	  @Autowired
	  UserRepository userRepository;
	  
	  private String success = "{\"message\":\"success\"}";
	  private String failure = "{\"message\":\"failure\"}";
	  
	  //create user
	  @PostMapping(path = "/users/{userId}/profiles/create")
	  String createUserProfile(@PathVariable int userId, @RequestBody Profile req) {
		  
		  User user = userRepository.getOne(userId);
		  if(user == null) {
			  return failure;
		  }
		  Profile profile = new Profile();
		  profile.setGender(req.getGender());
		  profile.setPhone(req.getPhone());
		  profile.setZipdcode(req.getZipcode());
		  profile.setAge(req.getAge());
		  profile.setSelfIntroduction(req.getSelfIntroduction());
		  profileRepository.save(profile);
		  user.setProfile(profile);
		  userRepository.save(user);
		  return success;
	  }
	  @PostMapping(path = "/users/{id}/profiles")
	  Profile showProfiles(@PathVariable int id) {
	     User user = userRepository.findById(id);
	     if(user == null) {
	    	 return null;
	     }
	     return user.getProfile();   
	  }
	  
	  @PutMapping(path = "/users/{userId}/profiles/update")
	  Profile updateProfile(@PathVariable int userId, @RequestBody Profile req) {
		  Profile profile = profileRepository.findById(userId);
		  if(profile == null) {
			  return null;
		  }
		  profile.setGender(req.getGender());
		  profile.setPhone(req.getPhone());
		  profile.setZipdcode(req.getZipcode());
		  profile.setAge(req.getAge());
		  profile.setSelfIntroduction(req.getSelfIntroduction());
		  profileRepository.save(profile);
		  return profileRepository.findById(userId);
	  }
	 
}



