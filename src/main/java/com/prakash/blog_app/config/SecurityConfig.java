package com.prakash.blog_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prakash.blog_app.security.CustomeUserDetailsService;
import com.prakash.blog_app.security.JwtAuthenticationEntryPoint;
import com.prakash.blog_app.security.JwtAuthenticationFilter;



@Configuration
public class SecurityConfig {
	@Autowired
	CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
//		httpSecurity
//		.csrf(customizer->customizer.disable())
//		.authorizeHttpRequests((requests) -> requests
////			.requestMatchers("/", "/login").permitAll()
//			.anyRequest().authenticated()
//		)
////		.formLogin(Customizer.withDefaults())
//		.httpBasic(Customizer.withDefaults());
		
		http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request-> request.requestMatchers("/","/auth/login")
        		                     .permitAll().anyRequest()
        		                     .authenticated())
       .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

//		
		
		return http.build();
		
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customeUserDetailsService).passwordEncoder(passEncoder());
		
	}
	
	@Bean 
	public PasswordEncoder passEncoder() {
		return new  BCryptPasswordEncoder();
	}
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	

}
