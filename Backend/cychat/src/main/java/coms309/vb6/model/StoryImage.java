package coms309.vb6.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "story_images")
public class StoryImage {
	@Id
	@Column(name = "story_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "image_path")
	private String imagePath;
	
    @ManyToOne
	@JoinColumn(name = "story_id")
    @JsonIgnore
    private Story story; 
    public StoryImage() {
    	
    }
    public StoryImage(String image_path)
    {
    	this.imagePath = image_path;
    }
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath; 
	}
	public String getImagePath()
	{
		return imagePath;
	}
	public void setStory(Story story)
	{
		this.story = story; 
	}
	public Story getStory()
	{
		return story;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}
