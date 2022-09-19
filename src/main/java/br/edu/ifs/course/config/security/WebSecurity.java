package br.edu.ifs.course.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.ifs.course.services.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity {
	
	@Autowired
	AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(authenticationConfiguration)))
				.addFilter(new JWTValidationFilter(authenticationManager(authenticationConfiguration), userDetailServiceImpl))
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
