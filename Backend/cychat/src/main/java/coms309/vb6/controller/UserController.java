package coms309.vb6.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coms309.vb6.model.User;
import coms309.vb6.repository.ProfileRepository;
import coms309.vb6.repository.UserRepository;

@RestController
public class UserController {
	
//need more studies on repository
  @Autowired
  UserRepository userRepository;
  @Autowired
  ProfileRepository profileRepository; 
  
  private String success = "{\"message\":\"success\"}";
  private String failure = "{\"message\":\"failure\"}";
  private String user_failure =  "{\"message\":\"user not found\"}";
  private String password_failure =  "{\"message\":\"incorrect password\"}";
//  private String failure = "{\"message\":\"failure\"}";
  //show users
  @GetMapping(path = "/users")
  public List<User> showUsers() {
      return userRepository.findAll();
  }
  
  //create user
  @PostMapping(path = "/users/create")
  String createUser(@RequestBody User user) {
	  if(checkUserExist(user.getEmail()) != null) {
		  return "{\"message\":\"failure, email exist\"}";
	  }
	  userRepository.save(user);
	  return success;
  }
  


  //show single user by id
  @PostMapping(path = "/users/{id}")
  public User findpersonById(@PathVariable("id")int id) {  
       return userRepository.findById(id);
  }
  //update user information
  @PutMapping("/users/{id}")
  User updateUser(@PathVariable int id, @RequestBody User request){
      User user = userRepository.findById(id);
      if(user == null)
          return null;
      user.setEmail(request.getEmail());
      user.setPassword(request.getPassword());
      user.setFirstName(request.getFirstName());
      user.setLastName(request.getLastName());
      userRepository.save(user);
      return userRepository.findById(id);
  }   
  
  //delete user
  @DeleteMapping(path = "/users/{id}")
  String deleteUser(@PathVariable int id){
	  if(profileRepository.findById(id)!=null) {
	   profileRepository.deleteById(id);
	  }
	  if(userRepository.findById(id)!=null) {
      userRepository.deleteById(id);
	  }
      return success;
  }
  @PostMapping(path = "/users/login")
  public String Login(@RequestBody User req) {
	   User user = checkUserExist(req.getEmail()); 
	  if(user != null) {
		  if(user.getPassword().equals(req.getPassword())){
			return  "{\"id\":\""+user.getId()+"\"}";
		  }
		  else {
			  return password_failure;
		  }
	  }
	  else {
		  return user_failure;
	  }
  }
  //send friends request
  @PostMapping(path = "/users/{id}/request")
  String sendFriendsRequest(@PathVariable int id, @RequestBody User user) {
	  User requester = userRepository.findById(id);
	  User potential_friend = checkUserExist(user.getEmail());
	  if(requester.getPotentialFriends().contains(potential_friend)){
		  return failure;
	  }
	  if(requester != null && potential_friend != null) {
		  Set<User> requesters = potential_friend.getPotentialFriends();
		  requesters.add(requester);
		  potential_friend.setPotentialFriends(requesters);
		  userRepository.save(potential_friend);
		  return success;
	  }
	  else {
		  return failure;
	  }
  }
  //approve or reject friends request based of user response
  @PostMapping(path = "/users/{id}/approveRequest/{req}")
  String approveFriendsRequest(@PathVariable int id, @PathVariable int req, @RequestBody User user) {
	  User responser = userRepository.findById(id);
	  User potential_friend = checkUserExist(user.getEmail());
	  if(responser == null || potential_friend == null) {
		  return failure;
	  }
	  else {
	  Set<User> requesters = responser.getPotentialFriends();
	  if(req == 0) {
		 
		 if(!requesters.contains(potential_friend)) {
			 return failure;
		 }
		 else {
			 requesters.remove(potential_friend);
			 responser.setPotentialFriends(requesters);
			 userRepository.save(responser);
			 return success;
		 }
	  }
	  else if(req == 1){
		  requesters.remove(potential_friend);
		  responser.setPotentialFriends(requesters);
		  Set<User>friends = responser.getFriends();
		  friends.add(potential_friend);
		  responser.setFriends(friends);
		  userRepository.save(responser);
		  friends = potential_friend.getFriends();
		  friends.add(responser);
		  potential_friend.setFriends(friends);
		  userRepository.save(potential_friend);
		  return success;
		  
	  }
	  else {
		  return failure;
	  }
	  }
  }
  @PostMapping(path="/users/{id}/disconnect")
  String disconnect(@PathVariable int id, @RequestBody User req) {
	  User requester = userRepository.findById(id);
	  User outdated_friend = checkUserExist(req.getEmail());
	  if(requester == null || outdated_friend == null) {
		  return failure;
	  }
	  else {
		  Set<User>friends = requester.getFriends();
		 if(!friends.contains(outdated_friend)) {
			 return failure;
		 }
		 else {
			 friends.remove(outdated_friend);
			 requester.setFriends(friends);
			 userRepository.save(requester);
			 friends = outdated_friend.getFriends();
			 friends.remove(requester);
			 outdated_friend.setFriends(friends);
			 userRepository.save(outdated_friend);
			 return success;
		 }
	  }
  }
  //list friends by userId
  @PostMapping(path="/users/{id}/friends")
  public Set<User> showFriends(@PathVariable int id){
	  User user = userRepository.findById(id);
	  return user.getFriends();
  }
  
  @PostMapping(path="/users/{id}/potential_friends")
  public Set<User>showFriendsRequests(@PathVariable int id){
	  User user = userRepository.findById(id);
	  return user.getPotentialFriends();
  }
  

  public User checkUserExist(String email) {
	 List<User> u =  this.showUsers(); 
	 User user = null;  
	 for(int i = 0; i < u.size(); i++) {
		 if(u.get(i).getEmail().equals(email)) {
			 user= u.get(i); 
			 break; 
		 }
	 }
	 return user; 
  }
}