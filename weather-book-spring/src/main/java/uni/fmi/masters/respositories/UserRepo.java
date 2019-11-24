package uni.fmi.masters.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.beans.UserBean;

@Repository
public interface UserRepo extends JpaRepository<UserBean, Integer>{
	
	UserBean findUserByUsernameAndPassword(String username,
			String password);
	
	List<UserBean> findByUsernameContaining(String username);
	
	UserBean findByUsername(String username);
	
	UserBean findByEmail(String email);
	
}
