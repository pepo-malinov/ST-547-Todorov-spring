package uni.fmi.masters;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
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
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll()
		.requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
		.and().csrf().disable();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {		
		UserDetails user = 
				User.withDefaultPasswordEncoder()
				.username("mariika").password("password").roles("USER").build();
		
		UserDetails admin = 
				User.withDefaultPasswordEncoder().username("admincho")
				.password("password").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(user, admin);	
		
	}
}
