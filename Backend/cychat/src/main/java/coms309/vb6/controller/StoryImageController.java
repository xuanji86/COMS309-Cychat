package coms309.vb6.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;


import coms309.vb6.model.Story;
import coms309.vb6.model.User;
import coms309.vb6.model.StoryImage;
import coms309.vb6.repository.StoryImageRepository;
import coms309.vb6.repository.StoryRepository;
import coms309.vb6.repository.UserRepository;

@RestController
public class StoryImageController {

  private String failure = "{\"message\":\"failure\"}";
  private String success = "{\"message\":\"success\"}";
  private static String uploadPath = "/uploadedImage/";
  @Autowired
  HttpServletRequest httpServletRequest;
  @Autowired
  StoryRepository storyRepository;
  @Autowired
  StoryImageRepository storyImageRepository; 
  @Autowired
  UserRepository userRepository;
  
  @PostMapping(path = "/users/{id}/stories/{story_id}/image")
  String createStoryImage(@PathVariable int id, @PathVariable int story_id, @RequestParam("image") MultipartFile imageFile) throws IllegalStateException, IOException, FileNotFoundException{
      if(imageFile.isEmpty()) 
          return failure;      
	  String fullPath = httpServletRequest.getServletContext().getRealPath(uploadPath + story_id +"/");
      StoryImage storyImage = new StoryImage(fullPath);
      Story story = storyRepository.findById(story_id);
      User user = userRepository.findById(id);
      if(story == null || user == null)
      {
    	  return failure;
      }
      if(!story.getUser().equals(user)) {
    	  return failure;
      }
      storyImage.setStory(story);
      storyImageRepository.save(storyImage);
      fullPath+=(storyImage.getId()+"_"+imageFile.getOriginalFilename());
      System.out.println(fullPath);
      if (!new File(fullPath).exists())
      {
         System.out.println(new File(fullPath).mkdirs());
      }
      storyImage.setImagePath(fullPath);
      storyImageRepository.save(storyImage);
      
      List<StoryImage>images = story.getImages();
      images.add(storyImage);
      story.setImages(images);
      storyRepository.save(story);
      File tempFile = new File(fullPath);
      imageFile.transferTo(tempFile); 
      return "{\"message\":\"" + storyImage.getId()+ "\"}";
  }
  
  
  
  //Download the story image requested
  @GetMapping(path = "/stories/{story_id}/image/{image_id}")
  public ResponseEntity<Resource> getImage(@PathVariable int story_id, @PathVariable int image_id) throws IOException
  {
	  Story story = storyRepository.findById(story_id);
	  StoryImage storyImage = storyImageRepository.findById(image_id);
	  if(story == null || storyImage == null) {
		  return null; 
	  }
	  File file = new File(storyImage.getImagePath());
	  if(!file.exists()) {
          return null;
	  }
      String[] splitPath = storyImage.getImagePath().split("/");
      String fileName = splitPath[splitPath.length-1];
      // add headers to state that a file is being downloaded
      HttpHeaders header = new HttpHeaders();
      header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
      header.add("Cache-Control", "no-cache, no-store, must-revalidate");
      header.add("Pragma", "no-cache");
      header.add("Expires", "0");

      // convert the file into bytearrayresource format to send to the front end with the file
      Path path = Paths.get(file.getAbsolutePath());
      ByteArrayResource data = new ByteArrayResource(Files.readAllBytes(path));

      // send the response entity back to the front end with the 
      return ResponseEntity.ok()
              .headers(header)
              .contentLength(file.length())
              .contentType(MediaType.parseMediaType("application/octet-stream"))
              .body(data);
  }
  
  @PostMapping(path="/image/{image_id}")
  public String getImagePath(@PathVariable int image_id)
  {
	  return storyImageRepository.findById(image_id).getImagePath();
  }
  
  }

