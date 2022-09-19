package br.edu.ifs.course.config.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifs.course.entities.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			User loginDetails = new ObjectMapper().readValue(request.getInputStream(), User.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword(), loginDetails.getAuthorities()));
		} catch (IOException e) {
			throw new RuntimeException("User cannot be authenticated.");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		User loginDetails = (User) authResult.getPrincipal();
		
		String token = JWT.create().withSubject(loginDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET));

		response.addHeader(AuthenticationConfigConstants.HEADER_STRING,
				AuthenticationConfigConstants.TOKEN_PREFIX + token);
	}

}
