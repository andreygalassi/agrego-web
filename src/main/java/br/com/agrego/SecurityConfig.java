package br.com.agrego;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // (debug = true)
public class SecurityConfig {

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
//			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
//				.requestMatchers("/", "/home").permitAll()
				.requestMatchers("/login").anonymous()
				.requestMatchers("/layout").permitAll()
//				.requestMatchers("/").permitAll()
				.requestMatchers("/").authenticated()
				.requestMatchers("/static/**").permitAll()
				.requestMatchers("/webjars/**").permitAll()
				.anyRequest().authenticated()
			)
//			.httpBasic(withDefaults())
//			.formLogin(withDefaults())
			.formLogin((form) -> form
					.loginPage("/login")
					.permitAll(false)
					.failureForwardUrl("/login")
				)
			.logout((logout) -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
//					.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
//					.addLogoutHandler(new SecurityContextLogoutHandler())
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.permitAll())
			;
		// @formatter:on
		return http.build();
	}

	// @formatter:off
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		UserDetails admin = User.withUsername("admin")
//				.password(passwordEncoder().encode("adminPass"))
				.password("password")
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user,admin);
	}
//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
//		return http.getSharedObject(AuthenticationManagerBuilder.class)
//				.userDetailsService(userDetailsService())
//				.passwordEncoder(bCryptPasswordEncoder)
//				.and()
//				.build();
//	}
	// @formatter:on
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}