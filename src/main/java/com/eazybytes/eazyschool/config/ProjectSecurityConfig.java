package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig /* extends WebSecurityConfigurerAdapter */ {

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * // Permit All Requests inside the Web Application http.authorizeRequests().
	 * anyRequest().permitAll(). and().formLogin() .and().httpBasic();
	 * 
	 * // Deny All Requests inside the Web Application
	 *//*
		 * http.authorizeRequests(). anyRequest().denyAll(). and().formLogin()
		 * .and().httpBasic();
		 *//*
			 * }
			 */

	/**
	 * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to
	 * encourage users to move towards a component-based security configuration. It
	 * is recommended to create a bean of type SecurityFilterChain for security
	 * related configurations.
	 * 
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		// Permit All Requests inside the Web Application
//            http.authorizeRequests().anyRequest().permitAll().
//                    and().formLogin()
//                    .and().httpBasic();

		// Deny All Requests inside the Web Application
//            http.authorizeRequests().anyRequest().denyAll().
//                    and().formLogin()
//                    .and().httpBasic();
		
//		http.csrf().disable()  //EN VERSIÓN 3.0.0. DEL PARENT
//        .authorizeHttpRequests()
//        .requestMatchers("/home").permitAll()
//        .requestMatchers("/holidays/**").permitAll()
//        .requestMatchers("/contact").permitAll()
//        .requestMatchers("/saveMsg").permitAll()
//        .requestMatchers("/courses").permitAll()
//        .requestMatchers("/about").permitAll()
//        .requestMatchers("/assets/**").permitAll()
//        .and().formLogin().and().httpBasic();

		http.csrf().disable()
        	.authorizeRequests()
        	.mvcMatchers("/home").permitAll()
        	.mvcMatchers("/holidays/**").permitAll()
        	.mvcMatchers("/contact").permitAll()
        	.mvcMatchers("/saveMsg").permitAll()
        	.mvcMatchers("/courses").authenticated()
        	.mvcMatchers("/about").permitAll()
        	.and().formLogin().and().httpBasic();        

		return http.build();

	}
	
	@Bean
    @Description("In memory Userdetails service registered since DB doesn't have user table ")
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("54321"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
//	@Bean  //ANTIGUO
//    public InMemoryUserDetailsManager userDetailsService() {
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .roles("USER")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("54321")
//                .roles("USER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
	
	/* @Override  //MAS ANTIGUO
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("USER")
                .and()
                .withUser("admin").password("54321").roles("USER", "ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/

}
