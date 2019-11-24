package uni.fmi.masters.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import uni.fmi.masters.WebSecurityConfig;
import uni.fmi.masters.beans.UserBean;
import uni.fmi.masters.respositories.UserRepo;

@RestController
public class Login {

	private UserRepo userRepo;
	private WebSecurityConfig webSecurityConfig;

	public Login(UserRepo userRepo) {
		this.userRepo = userRepo;
		webSecurityConfig = new WebSecurityConfig();
	}

	@PostMapping(path = "/register")
	public UserBean register(@RequestParam(value = "email") String email,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
			@RequestParam(value = "repeatPassword") String repeatPassword) {

		if (password.equals(repeatPassword)) {
			UserBean user = new UserBean(username, email, hashMe(password));

			return userRepo.saveAndFlush(user);
		} else {
			return null;
		}
	}

	@PostMapping(path = "/login")
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpSession session) {

		UserBean user = userRepo.findUserByUsernameAndPassword(username, hashMe(password));

		if (user != null) {
			session.setAttribute("user", user);
			
			UserDetails userDetails = 
					webSecurityConfig.userDetailsService()
					.loadUserByUsername(user.getUsername());
			
			if(userDetails != null) {
				Authentication authentication = 
						new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(),
								userDetails.getPassword(),
								userDetails.getAuthorities()
								);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				ServletRequestAttributes attr = (ServletRequestAttributes)
						RequestContextHolder.currentRequestAttributes();
				
				HttpSession httpSession = attr.getRequest().getSession(true);
				httpSession.setAttribute(
						"SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());			
			}
			

			return "home.html";
		} else {
			return "error.html";
		}

	}
	
	@GetMapping(path = "/whoAmI")
	public ResponseEntity<Integer> whoAmI(HttpSession session){
		
		UserBean user = (UserBean) session.getAttribute("user");
		
		if(user != null) {
			return new ResponseEntity<>(user.getId(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(0, HttpStatus.UNAUTHORIZED);	
	}
	

	private String hashMe(String text) {

		StringBuilder sb = new StringBuilder();

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(text.getBytes());

			byte[] bytes = md.digest();

			for (int i = 0; i < bytes.length; i++) {
				sb.append((char) bytes[i]);
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

}
