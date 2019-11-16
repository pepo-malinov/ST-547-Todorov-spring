package uni.fmi.masters.rest;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.beans.UserBean;
import uni.fmi.masters.respositories.UserRepo;

@RestController
public class Login {
	
	private UserRepo userRepo;
	
	public Login(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@PostMapping(path = "/register")
	public UserBean register(
			@RequestParam(value = "email") String email, 
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "repeatPassword") String repeatPassword) {
		
		if(password.equals(repeatPassword)) {
			UserBean user = new UserBean(username, email, password);
			
			return userRepo.saveAndFlush(user);
		}else {
			return null;
		}
	}
	
	@PostMapping(path = "/login")
	public String login(
			@RequestParam(value ="username") String username,
			@RequestParam(value = "password") String password,
			HttpSession session){
		
		UserBean user = userRepo.findUserByUsernameAndPassword(username, password);
		
		if(user != null) {
			
			session.setAttribute("user", user);			
			
			return "home.html";
		}else { 
			return "error.html";
		}
		
	}

}
