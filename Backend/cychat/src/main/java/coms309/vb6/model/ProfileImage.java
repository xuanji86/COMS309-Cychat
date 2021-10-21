package coms309.vb6.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "profileImage")
public class ProfileImage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_image_id")
	private Integer id;
	
	@Column(name = "profile_image_path")
	private String profileImagePath;
	
	 @OneToOne
	 @JsonIgnore
     private Profile profile;
	 
    public ProfileImage() {
    	
    }
    public ProfileImage(String profileImagePath) {
    	this.profileImagePath = profileImagePath;
    }
    
    public Integer getId() {
    	return id; 
    }
    public void setId(int id) {
    	this.id = id; 
    }
	
	public void setProfile(Profile profile)
	{
		this.profile = profile; 
	}
    public Profile getProfile() 
    {
    	return profile;
    }
    
    public void setImagePath(String profileImagePath)
  	{
  		this.profileImagePath = profileImagePath; 
  	}
  	public String getImagePath()
  	{
  		return profileImagePath;
  	}
}
