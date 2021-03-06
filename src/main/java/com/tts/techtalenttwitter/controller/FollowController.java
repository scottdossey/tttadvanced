package com.tts.techtalenttwitter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalenttwitter.model.User;
import com.tts.techtalenttwitter.service.UserService;

@Controller
public class FollowController {
	@Autowired
	private UserService userService;
	
	//All our follow controller is going to do currently
	//is process requests for follows.
	//and to request a follow....all a user has to do
	//is visit "/follow/{username}" with a POST request.
	
	@PostMapping(value= "/follow/{username}")
	public String follow(@PathVariable(value="username") String username,
			             HttpServletRequest request) {
		User loggedInUser = userService.getLoggedInUser();
		User userToFollow = userService.findByUsername(username);
		List<User> followers = userToFollow.getFollowers();
		followers.add(loggedInUser);
		userToFollow.setFollowers(followers);
		userService.save(userToFollow);
				
		return "redirect:" + request.getHeader("Referer");		
	}
	
	@PostMapping(value= "/unfollow/{username}")
	public String unfollow(@PathVariable(value="username") String username,
			             HttpServletRequest request) {
		User loggedInUser = userService.getLoggedInUser();
		User userToUnfollow = userService.findByUsername(username);		
		List<User> followers = userToUnfollow.getFollowers();
		followers.remove(loggedInUser);
		userToUnfollow.setFollowers(followers);
		userService.save(userToUnfollow);
				
		return "redirect:" + request.getHeader("Referer");		
	}	
}
