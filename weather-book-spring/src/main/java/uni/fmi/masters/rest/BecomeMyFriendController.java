package uni.fmi.masters.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.beans.NotificationBean;
import uni.fmi.masters.beans.UserBean;
import uni.fmi.masters.respositories.NotificationRepo;
import uni.fmi.masters.respositories.UserRepo;


@RestController
public class BecomeMyFriendController {
	UserRepo userRepo;
	NotificationRepo notificationRepo;
	

	public BecomeMyFriendController(UserRepo userRepo,
			NotificationRepo notificationRepo) {
		this.userRepo = userRepo;
		this.notificationRepo = notificationRepo;
	}
	
	
	@GetMapping(path = "/user/sendFriendRequest")
	public ResponseEntity<Boolean> sendFriendRequest(
			@RequestParam(value = "forUserId") int forUserId,
			HttpSession session
			){
		
		UserBean user = (UserBean) session.getAttribute("user");
		
		if(user != null) {
			
			Optional<UserBean> forUser = userRepo.findById(forUserId);
			
			if(forUser.isPresent()) {
				
				NotificationBean nb = 
						notificationRepo.findByFromUserIdAndToUserId(user.getId(),
								forUser.get().getId());
				
				if(nb == null) {
					
					nb = new NotificationBean();
					
					nb.setStatus("Please");
					nb.setFromUser(user);
					nb.setToUser(forUser.get());
					nb.setDate(new Date());
					
					notificationRepo.saveAndFlush(nb);
					
					return new ResponseEntity<>(true, HttpStatus.OK);
					
				}
				
				return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
				
				
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);		
		
	}
	
	@GetMapping(path = "/user/search")
	public ResponseEntity<List<UserBean>> findUsers(
			@RequestParam(value = "username") String username,
			HttpSession session
			){
		
		UserBean user = (UserBean) session.getAttribute("user");
		
		if(user != null) {
			
			
			
			List<UserBean> users = userRepo.findByUsernameContaining(username);
		
			return new ResponseEntity<>(users, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
	}

}
