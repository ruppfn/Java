package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.UserDetailsServiceImplementation;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
@ComponentScan("com.example.demo")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplementation userDetailsService;
		
	public SecurityConfig() {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				.antMatchers(HttpMethod.POST, "/user/new").permitAll()
				.antMatchers(HttpMethod.GET, "/user").hasAuthority("ADMIN")
				.antMatchers("/persona").hasAnyAuthority("USER","ADMIN")
				.antMatchers(HttpMethod.DELETE, "/persona/{id}").hasAuthority("ADMIN")
				.anyRequest().authenticated();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
			return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
}
