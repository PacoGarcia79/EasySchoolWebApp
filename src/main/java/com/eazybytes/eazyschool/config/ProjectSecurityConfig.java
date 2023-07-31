package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
public class ProjectSecurityConfig {

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

		http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/**").and() //EN VERSIÓN 3.0.0. DEL PARENT SE USA requestMatchers (revisar código actual
        	.authorizeRequests()
        	.mvcMatchers("/dashboard").authenticated()
        	.mvcMatchers("/displayMessages").hasRole("ADMIN")
        	.mvcMatchers("/admin/**").hasRole("ADMIN")
        	.mvcMatchers("/closeMsg/**").hasRole("ADMIN")
        	.mvcMatchers("/displayProfile").authenticated()
            .mvcMatchers("/updateProfile").authenticated()
        	.mvcMatchers("", "/", "/home").permitAll()
        	.mvcMatchers("/holidays/**").permitAll()
        	.mvcMatchers("/contact").permitAll()
        	.mvcMatchers("/saveMsg").permitAll()
        	.mvcMatchers("/courses").permitAll()
        	.mvcMatchers("/about").permitAll()
        	.mvcMatchers("/login").permitAll()     
        	.mvcMatchers("/logout").permitAll()
        	.mvcMatchers("/public/**").permitAll()
        	.mvcMatchers("/assets/**").permitAll()
        	.and().formLogin().loginPage("/login")
        	.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
        	.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()          	
        	.and().httpBasic();        
				
		return http.build();

	}
	
//	@Bean //NO SE NECESITA SI COGEMOS LAS CREDENCIALES DESDE BBDD
//    @Description("In memory Userdetails service registered since DB doesn't have user table ")
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("12345"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("54321"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
