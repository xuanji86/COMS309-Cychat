package coms309.vb6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import coms309.vb6.model.Story;
import coms309.vb6.model.StoryImage;

@Repository
public interface StoryImageRepository extends JpaRepository<StoryImage, Integer> {
	 StoryImage findById(int id);
	 void deleteById(int id);
	 @Query("select si from StoryImage si where si.story = :story")
	 List<StoryImage>findByStory(Story story); 
}