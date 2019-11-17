package uni.fmi.masters.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"comments"})
public class UserBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", nullable = false, unique = true, length = 20)
	private String username;
	
	@Column(name = "email", length = 250)
	private String email;
	
	@Column(name = "password", length = 40, nullable = false)
	private String password;
	
	@Column(name = "avatar", length = 250)
	private String avatar;
	
	@OneToMany(mappedBy = "user" ,
			fetch = FetchType.EAGER)
	private List<CommentBean> comments;
	
	public UserBean() {	}
	
	public UserBean(String username, String email, String password) {
		this.email = email;
		this.password = password;
		this.username = username;		
	}	
	
	public List<CommentBean> getComments() {
		return comments;
	}

	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
		
	
	
}
