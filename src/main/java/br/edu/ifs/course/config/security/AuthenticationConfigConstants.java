package br.edu.ifs.course.config.security;

public class AuthenticationConfigConstants {
	
	public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
	public static final String SECRET = System.getenv("JWTSECRET");
	public static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000; // 1 day 
}
