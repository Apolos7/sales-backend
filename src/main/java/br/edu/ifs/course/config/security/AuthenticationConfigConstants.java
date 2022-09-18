package br.edu.ifs.course.config.security;

public class AuthenticationConfigConstants {
	
	public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
	public static final String SECRET = "98743054-1f1e-4331-bc5e-1a8b0e125f0e";
	public static final long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000; // 1 day 
}
