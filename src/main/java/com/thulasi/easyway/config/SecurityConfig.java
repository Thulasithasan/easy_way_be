package com.thulasi.easyway.config;

import com.thulasi.easyway.component.AuthEntryPoint;
import com.thulasi.easyway.component.ExceptionLoggingFilter;
import com.thulasi.easyway.component.JwtAuthFilter;
import com.thulasi.easyway.constant.AuthConstants;
import com.thulasi.easyway.security.OAuth2SuccessHandler;
import com.thulasi.easyway.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@ConditionalOnMissingBean(name = "EPSecurityConfig")
public class SecurityConfig {

	@NonNull
	private final JwtAuthFilter jwtAuthFilter;

	@NonNull
	private final UserDetailsService userDetailsService;

	@NonNull
	private final AuthEntryPoint authEntryPoint;

	@NonNull
	private final ExceptionLoggingFilter exceptionLoggingFilter;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	// {
	// http.cors(Customizer.withDefaults());
	// http.csrf(AbstractHttpConfigurer::disable)
	// .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
	// .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
	// .authorizeHttpRequests(auth -> auth
	// .requestMatchers("/v1/auth/**", "/v3/api-docs/**", "/swagger-ui.html",
	// "/swagger-ui/**",
	// "/ws/**", "/health", "/api/**", "v1/challenges/fetch-all-challenge-types" )
	// .permitAll()
	// .anyRequest()
	// .authenticated())
	// .oauth2Login(oauth2 -> oauth2
	// .successHandler(oAuth2SuccessHandler())
	// );
	//// .oauth2Login(oauth2 -> oauth2
	//// .loginPage("/api/auth/google/login")
	//// .defaultSuccessUrl("/v1/auth/oauth2/success", true)
	//// );
	//
	// http.addFilterBefore(exceptionLoggingFilter,
	// UsernamePasswordAuthenticationFilter.class);
	// http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	// http.authenticationProvider(authenticationProvider());
	//
	// return http.build();
	// }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2SuccessHandler successHandler)
			throws Exception {
		http.cors(Customizer.withDefaults());
		http.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
			.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/v1/auth/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/ws/**",
						"/health", "/api/**", "v1/challenges/fetch-all-challenge-types", "/v1/**",
						"/v1/stocks/home-products", "/v1/stocks/home-product-info/*")
				.permitAll()
				.anyRequest()
				.authenticated())
			.oauth2Login(oauth2 -> oauth2.successHandler(successHandler));

		http.addFilterBefore(exceptionLoggingFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(authenticationProvider());

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration
			.setAllowedHeaders(Arrays.asList("authorization", "content-type", AuthConstants.RESET_DATABASE_API_HEADER));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
