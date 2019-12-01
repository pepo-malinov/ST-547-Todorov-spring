package uni.fmi.masters;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import uni.fmi.masters.beans.RoleBean;
import uni.fmi.masters.beans.UserBean;

public class UserPrincipal implements UserDetails {

	private UserBean user;
	private Set<GrantedAuthority> authorities;
	
	public UserPrincipal(UserBean user, Set<RoleBean> roles) {
		this.user = user;
		authorities = new HashSet<GrantedAuthority>();
		insertRoles(roles);
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	private void insertRoles(Set<RoleBean> roles) {
		for(RoleBean role:roles) {
			authorities
			.add(new SimpleGrantedAuthority(role.getCode()));
		}
		if(authorities.isEmpty())
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
	}

}
