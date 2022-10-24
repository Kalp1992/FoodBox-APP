package com.foodBox.JWTConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.foodBox.Repository.UserRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
	    prePostEnabled = true, securedEnabled = true, jsr250Enabled = true
	)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserRepository userRepo;
	@Autowired
	CorsConfigurationSource webMvcConfig;
	
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
	@Override
    protected void  configure(HttpSecurity http) throws Exception {
      
//        http.cors().configurationSource(new CorsConfigurationSource() {
//
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedHeaders(Collections.singletonList("*"));
//                config.setAllowedMethods(Collections.singletonList("*"));
//                config.addAllowedOrigin("*");
//                config.setAllowCredentials(true);
//                return config;
//            }
//        });
		http.csrf().disable();
		http.cors();
//        http.headers().frameOptions().disable().and().csrf().disable().headers()
//        // the headers you want here. This solved all my CORS problems! 
//        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
//        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET"))
//        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
//        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
//        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
        http.authorizeRequests()
        .antMatchers("/",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js")
            .permitAll()
        .antMatchers("/api/status/**","/api/login/user","/api/signup/**")
            .permitAll()
//        .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
//            .permitAll().antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
            .antMatchers(HttpMethod.GET, "/api/status/**")
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll() 
        .anyRequest()
            .authenticated();
            
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
                response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    ex.getMessage()
                );
            }
        );
 
      http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
      
    }
	
//	@Bean
//	public CorsFilter corsFilter() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    final CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // Provide list of origins if you want multiple origins
//	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//	    config.setAllowCredentials(true);
//	    source.registerCorsConfiguration("/**", config);
//	    return new CorsFilter(source);
//	}
//	
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(
	            username -> userRepo.findByEmail(username)
	                .orElseThrow(
	                    () -> new UsernameNotFoundException("User " + username + " not found.")));
	    }
	 
//	    @Bean
//		public PasswordEncoder passwordEncoder() {
//			return new BCryptPasswordEncoder();
//		}
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	
	
 
}
