package coms309.Users;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
	private Integer id;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "zipcode")
	private String zipcode;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "age")
    private String age;
    
    
	public User(String address,String email, String telephone, String zipcode, String age) {
		this.address = address;
		this.email = email; 
		this.telephone = telephone;
        this.zipcode = zipcode;
        this.age = age;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

    
    public String getEmail() {
    	return this.email; 
    }
    
    public void setEmail(String email) {
    	 this.email = email;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipdcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
    public String toString() {
        return new ToStringCreator(this)

        		.append("id", this.getId())
                .append("email", this.getEmail())
                .append("address", this.getAddress())
                .append("telephone", this.getTelephone())
                .append("zipcode", this.getZipcode())
                .append("age", this.getAge())
                .toString();
    }

    
}
