package br.com.agrego;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity//(debug = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeHttpRequests((requests) -> requests
//				.requestMatchers("/", "/home").permitAll()
				.requestMatchers("/login").anonymous()
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
					.failureForwardUrl("/")
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
		return new InMemoryUserDetailsManager(user);
	}
	// @formatter:on

}