package com.norticeboard.smc.security;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.norticeboard.smc.security.filter.JwtAuthenticationFilter;
import com.norticeboard.smc.security.handler.LoginSuccessHandler;
import com.norticeboard.smc.security.provider.JwtAuthenticationProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	DataSource dataSource;
	
	/*
	 * do not config security to static resources
	 */
	
	/*
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf()
		.disable();
		/*
		.authorizeRequests()
		.antMatchers("/home","/login","/login.do","/create_account","/create_account/*","/create/token","/get/subject").permitAll()
		.antMatchers("/admin").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login.do")
		.successForwardUrl("/home")
		.failureForwardUrl("/home")
		.permitAll()
		.and()
		.addFilterBefore(jwtAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
		*/
	}
	
	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select user_id,user_pw,user_name from user where user_id = ?")
			.authoritiesByUsernameQuery("");
	}
	*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter postAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
		postAuthenticationFilter.setFilterProcessesUrl("/login.do");
		postAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
		postAuthenticationFilter.afterPropertiesSet();
		return postAuthenticationFilter;
	}
	
	@Bean
	public JwtAuthenticationProvider JwtAuthenticationProvider() {
		return new JwtAuthenticationProvider();
	}
	
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	*/
}
