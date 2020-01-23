package com.example.demo.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	private final String header = "Authorization";
	private final String prefix = "Prefix ";// tiene un espacio al final
	private final String secret = "secretKey";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if (checkJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			e.printStackTrace();
			return;
		}
	}
	
	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(header).replace(prefix, "");
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
	
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(header);
		if (authenticationHeader == null || !authenticationHeader.startsWith(prefix)) {
			return false;
		} else {
			return true;
		}
	}

}
