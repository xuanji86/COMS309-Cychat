package coms309.vb6.model;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "stories")


public class Story {
	@Id
	@Column(name = "story_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "text_area")
	private String textArea;
	@Column(name = "location")
    private String location;
	
    @ManyToMany
	@JoinTable(name = "stories_likes", joinColumns = @JoinColumn(name = "story_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> likesList;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
    @OneToMany
    private List<StoryImage>story_images;
    
	@Column(name = "time_stamp")
	private Timestamp time_stamp;
	
	private String posted_user_name; 
    
    public Story() {
    }
    public Story(String textArea) { 	
        this.textArea = textArea; 
	}
	
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setTextArea(String textArea) {
    	this.textArea = textArea;
    }
    public String getTextArea() {
    	return textArea;
    }
    public void setLocation(String location) {
    	this.location = location;
    }
    public String getLocation() {
    	return location;
    }
    public void setLikes(Set<User>likes) {
    	likesList = likes;
    }
    public Set<User>getLikes(){
    	return likesList;
    }
    public User getUser() {
        return user;
    }
    public void setImages(List<StoryImage>story_images)
    {
    	this.story_images = story_images; 
    }
    public List<StoryImage>getImages(){
    	return story_images; 
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setUserName(String user_name) {
    	this.posted_user_name = user_name; 
    }
    public String getUserName() {
    	return posted_user_name;
    }
    public void setTimeStamp(Timestamp timestamp) {
    	this.time_stamp = timestamp;
    }
    public Timestamp getTimeStamp() {
    	return time_stamp;
    }
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("text_area", this.getTextArea())
                .toString();
    }

    
}
