package lv.venta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager createTestUsers() {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails userD1 = User.builder().username("vineta").password(encoder.encode("123")).authorities("USER").build();
		UserDetails userD2 = User.builder().username("janis").password(encoder.encode("321")).authorities("USER").build();
		UserDetails userD3 = User.builder().username("admin").password(encoder.encode("987")).authorities("ADMIN").build();

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(userD1, userD2, userD3);
		return manager;
	}
	
	@Bean
	public SecurityFilterChain configureEndPoints(HttpSecurity http) {
		
		http.authorizeHttpRequests(
				auth->auth
				.requestMatchers("/product/crud/all").permitAll()
				.requestMatchers("/product/crud/one?**").permitAll()
				.requestMatchers("/product/crud/all/**").permitAll()
				.requestMatchers("/product/crud/add").hasAuthority("ADMIN")
				.requestMatchers("/product/crud/update/**").hasAnyAuthority("ADMIN", "USER")
				.requestMatchers("/product/crud/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/product/filter/price/**").permitAll()
				// TODO uztaisit parejam kontrolieru funkcijasm
	);
		
		http.formLogin(auth->auth.permitAll());
		
		return http.build();
	}
}
