package uni.fmi.masters;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uni.fmi.masters.beans.RoleBean;
import uni.fmi.masters.beans.UserBean;
import uni.fmi.masters.respositories.UserRepo;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	private UserRepo _userRepo;

	public ApplicationUserDetailsService(UserRepo userRepo) {
		_userRepo= userRepo;
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		UserBean user = _userRepo.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException(username);
		
		Set<RoleBean> roles = user.getRoles();
		
		return new UserPrincipal(user, roles);
	}

}
