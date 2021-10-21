package coms309.Users;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
 @Autowired
 	
    private UserRepository userRepository;
 
 
 @PostMapping("/user")
 public String saveuser(User user) {
    userRepository.save(user);
    return "User " + user.getAge() + " saved"; 
   }
 
 @RequestMapping("/create")
 public String createUser(
                 @RequestParam(value = "email", defaultValue = "jdoe@iastate.edu")String email, 
                 @RequestParam(value = "telephone", defaultValue = "5157082255")String telephone, 
                 @RequestParam(value = "zipdcode", defaultValue = "50010")String zipcode, 
                 @RequestParam(value = "age", defaultValue = "20")String age,
        @RequestParam(value = "address", defaultValue = "ames")String address){
        
        User u = new User (email, telephone, zipcode, age, address);
        userRepository.save(u);
        return "Successfully created user "+email;
}
 
 @RequestMapping("/users")
 public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        list = userRepository.findAll();
        return list;
    }

 @RequestMapping("/getByUserName")
 public User getByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user;
    }
 
 
 
 
 
 public void alterPersonPro(){
		
		//Modify Personal Information
		((Button) $(alterPerson, "submit")).setOnAction(event -> {
	        alterPerson.resetErrorTip();
	        String sex;
	        String account = ((Label) $(alterPerson, "account")).getText();
	        String label = ((TextArea) $(alterPerson, "label")).getText();
	        String firstname = ((TextField) $(alterPerson, "first_name")).getText();
	        String lastname = ((TextField) $(alterPerson, "last_name")).getText();
	        String age = ((TextField) $(alterPerson, "age")).getText();
	        String zipcode = ((TextField) $(alterPerson, "zipcode")).getText();
	        String telephone = ((TextField) $(alterPerson, "telephone")).getText();
	        String nameRegExp = "^[a-z,A-Z,\\u4e00-\\u9fa5]{1,100}$";
	        String phoneRegExp = "^(((13[0-9])|(15[0-3][5-9])|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
	        String ageRegExp = "^\\d{1,3}$";
	        RadioButton man = ((RadioButton) $(alterPerson, "man"));
	        RadioButton woman = ((RadioButton) $(alterPerson, "woman"));
	        if (name.equals("") || phone.equals("") || age.equals("")) {
	            if (name.equals("")) {
	                alterPerson.setErrorTip("nameError", "No name entered");
	            }
	            if (phone.equals("")) {
	                alterPerson.setErrorTip("phoneError", "No phone number entered");
	            }
	            if (age.equals("")) {
	                alterPerson.setErrorTip("ageError", "No age entered");
	            }
	        } else if (!Pattern.matches(nameRegExp, name) || !Pattern.matches(phoneRegExp, phone) || !Pattern.matches(ageRegExp, age)) {
	            if (!Pattern.matches(nameRegExp, name)) {
	                alterPerson.setErrorTip("nameError", "Incorrect name format");
	            }
	            if (!Pattern.matches(phoneRegExp, phone)) {
	                alterPerson.setErrorTip("phoneError", "Telephone number format is wrong");
	            }
	            if (!Pattern.matches(ageRegExp, age)) {
	                alterPerson.setErrorTip("ageError", "The age is entered incorrectly, the age can only be a number");
	            }
	        } else {
                if (man.isSelected()) {
                    sex = "man";
                } else {
                    sex = "woman";
                }
                try {
                    userdata.setPhone(phone);
                    userdata.setLabel(label);
                    userdata.setSex(sex);
                    userdata.setAddress(address);
                    userdata.setName(name);
                    userdata.setAge(Integer.parseInt(age));
                    database.exec("UPDATE user SET label=?, name=?, age=?, address=?, phone=?, sex=?,head=? WHERE account=?", label, name, age, address, phone, sex, userdata.getHead(), account);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                homepage.setUserData("label", label);
                homepage.setUserData("name", name);
                homepage.setUserData("age", age);
                homepage.setUserData("address", address);
                homepage.setUserData("phone", phone);
                homepage.setUserData("sex", sex);
                setHeadPortrait(((Button) $(homepage, "head")), userdata.getHead());
                setHeadPortrait(((Button) $(homepage, "background")), "head1",userdata.getHead());
                setHeadPortrait(((Button) $(mainWindow, "individual")), userdata.getHead());
                mainWindow.setPersonalInfo(account,name,address,phone);
                alterPerson.close();
            }

        });
        alterHead();

		};
	



private void alterHead() {
	
}



	
}
 
 
 
 
 
 


