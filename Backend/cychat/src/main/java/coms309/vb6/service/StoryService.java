package coms309.vb6.service;

import org.springframework.beans.factory.annotation.Autowired;

import coms309.vb6.model.Story;
import coms309.vb6.repository.StoryRepository;

public class StoryService {
     @Autowired StoryRepository storyRepository; 
     
     public Story findById(int id) {
    	return storyRepository.findById(id);
     }
}
