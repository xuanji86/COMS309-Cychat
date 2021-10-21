package coms309.vb6.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profile")
public class Profile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_id")
	private Integer id; 
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Gender gender;

    private String email;

    private String zipcode;
	
    private String phone;

    private Integer age;
	
    private String selfIntroduction; 
	
	 @OneToOne
	 @JsonIgnore
    private User user;
	
//	 @OneToOne
//	 @JoinColumn(name = "profile_image_id")
//	 @JsonIgnore
//	private ProfileImage profileImage; 
	 
    public Profile() {
    	
    }
    public Profile(String email) {
		this.email = email; 
		this.zipcode = "50010";
		this.phone = "515-123-4567";
		this.gender = Gender.UNKNOWN;
		this.age = null; 
		this.selfIntroduction = null; 
	}
    public Profile(int id, String zipcode, String phone, Gender gender, int age, String selfIntroduction) {
    	this.id = id; 
    	this.gender = gender; 	
    	this.zipcode = zipcode; 
    	this.phone = phone; 
    	this.age = age; 
    	this.selfIntroduction = selfIntroduction; 
    }

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUser(User u) {
		this.user = u;
	}
    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipdcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public void setGender(Gender g) {
    	this.gender = g; 
    }
    public Gender getGender() {
    	return gender;
    }
    public String getPhone() {
    	return phone;
    }
    public void setPhone(String phone) {
    	this.phone = phone; 
    }
    
    public String getEmail() {
    	return this.email; 
    }
    
    public void setEmail(String email) {
    	 this.email = email;
    }
    
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getSelfIntroduction() {
    	return selfIntroduction; 
    }
    public void setSelfIntroduction(String selfIntroduction) {
    	this.selfIntroduction = selfIntroduction; 
    }
    
//    public void setProfileImage(ProfileImage profileImage)
//    {
//    	this.profileImage = profileImage;
//    }
//    public ProfileImage getProfileImage()
//    {
//    	return profileImage;
//    }
    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("zipcode", this.getZipcode())
                .append("phone", this.getPhone())
                .append("gender", this.getGender())
                .append("age", this.getAge())
                .append("selfIntroduction", this.getSelfIntroduction())
                .toString();
    }
	
}
