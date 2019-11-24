package uni.fmi.masters.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.beans.CommentBean;
import uni.fmi.masters.beans.UserBean;
import uni.fmi.masters.respositories.CommentRepo;

@RestController
public class Comment {
	
	CommentRepo commentRepo;
	
	public Comment(CommentRepo commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	
	@PostMapping(path = "/comment/add")
	public String addComment(
			@RequestParam(value = "comment") String comment,
			@RequestParam(value = "temp") double temp,
			@RequestParam(value = "selectCity") String city,
			@RequestParam(value = "image") String image,
			HttpSession session
			) {
		
		UserBean user = (UserBean) session.getAttribute("user");
			
		if(user != null) {
			CommentBean commentBean = new CommentBean();
			commentBean.setCity(city);
			commentBean.setComment(comment);
			commentBean.setTemp(temp);
			commentBean.setPicture(image);
			commentBean.setUser(user);
			
			commentBean =  commentRepo.saveAndFlush(commentBean);
			
			if(commentBean != null) {
				return commentBean.getId() + "";
			}
			
			return "-1";
			
		}else {
			return "error";
		}
		
	}
	
	@DeleteMapping(path = "/comment/delete")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Boolean> deleteComment(
			@RequestParam(value = "id") int id,
			HttpSession session
			){
		
		UserBean user = (UserBean)session.getAttribute("user");
		
		if(user == null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		
		Optional<CommentBean> optionalComment = commentRepo.findById(id);
		
		if(optionalComment.isPresent()) {
			
			CommentBean comment = optionalComment.get();
			
			if(comment.getUser().getId() == user.getId()) {
				commentRepo.delete(comment);
				
				return new ResponseEntity<>(true, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(false, HttpStatus.I_AM_A_TEAPOT);
			}
						
		}else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}		
	}
	
	
	@GetMapping(path = "/comment/all")
	public List<CommentBean> getAllComments(){
		return commentRepo.findAll();
	}

}
