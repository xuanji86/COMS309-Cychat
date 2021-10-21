package coms309.vb6.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name="password")
    private String password; 
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;  

    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="user_friends",
	joinColumns={@JoinColumn(name="user_id")},
	inverseJoinColumns={@JoinColumn(name="friend_id")})
	@JsonIgnore
	private Set<User>friends = new HashSet<User>();
    
    @ManyToMany(mappedBy="friends")
	@JsonIgnore
	private Set<User> friendsOf = new HashSet<User>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="requester_potentialFriends",
    joinColumns= {@JoinColumn(name="user_id")},
    inverseJoinColumns= {@JoinColumn(name="potential_friend_id")})
    @JsonIgnore
    private Set<User>potential_friends = new HashSet<User>();
    
    @ManyToMany(mappedBy="potential_friends")
    @JsonIgnore
    private Set<User> friendsRequest = new HashSet<User>();

    @OneToMany
    private List<Story>posted_stories;
    
    @ManyToMany(mappedBy="likesList")
    private Set<Story>liked_stories; 
    
    public User() {
    	
    }
    
	public User(String firstname, String lastname, String email, String password) {
		this.first_name = firstname; 	
		this.last_name = lastname;
		this.email = email; 
        this.password = password; 
	}
	public void setProfile(Profile p) {
		this.profile = p;
	}
	public Profile getProfile() {
		return profile;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getFirstName() {
        return this.first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    public String getLastName() {
        return this.last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
    public String getEmail() {
    	return this.email; 
    }
    
    public void setEmail(String email) {
    	 this.email = email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password; 
    }

    public void setFriends(Set<User>friends) {
    	this.friends = friends;
    }
    public Set<User> getFriends() {
    	return friends;
    }
    public void setPotentialFriends(Set<User>potential_friends) {
    	this.potential_friends = potential_friends;
    }
    public Set<User> getPotentialFriends(){
    	return potential_friends;
    }
    public List<Story>getPostedStories(){
    	return posted_stories;
    }
    public void setPostedStories(List<Story>posted_stories) {
    	this.posted_stories = posted_stories;
    }
    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("email", this.getEmail())
                .append("password", this.getPassword())
                .toString();
    }

    
}
