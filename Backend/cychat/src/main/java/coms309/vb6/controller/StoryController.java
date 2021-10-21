package coms309.vb6.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import coms309.vb6.model.Story;
import coms309.vb6.model.StoryImage;
import coms309.vb6.model.User;
import coms309.vb6.repository.StoryImageRepository;
import coms309.vb6.repository.StoryRepository;
import coms309.vb6.repository.UserRepository;

@RestController
public class StoryController {

    @Autowired
    StoryRepository storyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StoryImageRepository storyImageRepository; 
   
    private String success = "{\"Story\":\"success\"}";
    private String failure = "{\"Story\":\"failure\"}";

    @PostMapping(path="/users/{id}/posted_stories")
    List<Story>getSelfPostedStories(@PathVariable int id){
    	User user = userRepository.findById(id);
    	if(user == null) {
    		return null;
    	}
    	return storyRepository.findByIdByOrderBytime_stampDesc(user);
    }
    
    @PostMapping(path = "/users/{id}/stories")
    List<Story>getAllStories(@PathVariable int id){
    	User user = userRepository.findById(id);
    	if(user == null) {
    		return null;
    	}
    	Set<User>visible_users = user.getFriends();
    	visible_users.add(user);
      return storyRepository.findByIdByOrderBytime_stampDesc(visible_users);
    }
   
    @PostMapping(path = "/users/{id}/stories/create")
    String createStory(@PathVariable int id, @RequestBody Story story){
    	User user = userRepository.findById(id);
        if (story == null || user == null) {
            return failure;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        story.setTimeStamp(timestamp);
        story.setUserName(user.getFirstName()+ " " + user.getLastName());
        story.setUser(user); 
        storyRepository.save(story);
            
        return success;
    }
   
    //update story
    @PutMapping(path = "/users/{id}/stories/{story_id}")
    String updateStory(@PathVariable int story_id, @PathVariable int id, @RequestBody Story req){
    	Story story = storyRepository.findById(story_id);
    	User user = userRepository.findById(id);
        if(story == null || user == null) {
            return failure;
        }
        if(user.equals(story.getUser()))
        {
        	if(req.getTextArea()!=null) {
        	story.setTextArea(req.getTextArea());
        	}
        	if(req.getLocation()!=null) {
        	story.setLocation(req.getLocation());
        	}
        }
        storyRepository.save(story);
        return success;
    }
    
    //delete_stories
    @DeleteMapping(path = "/users/{id}/stories/{story_id}")
      String deleteStory(@PathVariable int id, @PathVariable int story_id){

    	Story story = storyRepository.findById(story_id);
    	User user = userRepository.findById(id);
        if(story == null || user == null) {
            return failure;
        }      
        List<StoryImage> images = storyImageRepository.findByStory(story);
        for(StoryImage image : images)
        {
        	File storyImage = new File(image.getImagePath());
        	if (storyImage != null && storyImage.exists())
                storyImage.delete();
        }
        List<Story> stories =  user.getPostedStories(); 
        stories.remove(story); 
        user.setPostedStories(stories);
        userRepository.save(user);
        
        story.setImages(null);
        story.setUser(null);
        story.setLikes(null);
        storyRepository.save(story);
        for(StoryImage image : images)
        {
            storyImageRepository.deleteById(image.getId());
        }
        storyRepository.deleteById(story_id);
        return success;
    }
   
    @PostMapping(path="/users/{user_id}/like_story/{story_id}")
    String likeStories(@PathVariable int user_id, @PathVariable int story_id) {
  	  User user = userRepository.findById(user_id); 
  	  Story story = storyRepository.findById(story_id);
  	  if(story == null || user == null) {
  		  return failure; 
  	  }	  
  	  Set<User>likes = story.getLikes();
  	  likes.add(user); 
  	  story.setLikes(likes);
  	  storyRepository.save(story);
  	  return success; 
    }
    
    @PostMapping(path="/stories/{story_id}/images")
    public List<StoryImage>getStoryImages(@PathVariable int story_id){
    	Story story = storyRepository.findById(story_id);
    	if(story == null) {
    		return null; 
    	}
    	return storyImageRepository.findByStory(story);
    }
}
