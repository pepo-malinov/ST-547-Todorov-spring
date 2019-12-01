package uni.fmi.masters;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	private ApplicationUserDetailsService _userDetailService;
	public WebSecurityConfig(ApplicationUserDetailsService userDetailsService) {
		// TODO Auto-generated constructor stub
		_userDetailService = userDetailsService;
	}
	
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll()
		.requestMatchers(EndpointRequest.toAnyEndpoint())
		.permitAll()
		.and().csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
		auth.userDetailsService(_userDetailService);
	}
	
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {		
		
		return _userDetailService;	
		
	}
}
