package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {
	 	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests((authz) -> authz
	                .requestMatchers("/", "/index.html", "/static/**",
	                    "/*.ico", "/*.json", "/*.png", "/api/user").permitAll()
	                .anyRequest().authenticated()
	            )
	            .csrf((csrf) -> csrf
	                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
	            )
	            .addFilterAfter(new CookieCsrfFilter(), BasicAuthenticationFilter.class)
	            .addFilterAfter(new WebFilter(), BasicAuthenticationFilter.class)
	            .oauth2Login();
	        return http.build();
	    }
	 
	 	@Bean
		public ClientRegistrationRepository clientRegistrationRepository() {
			return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
		}
	 	
	    @Bean
	    public RequestCache refererRequestCache() {
	        return new HttpSessionRequestCache() {
	            @Override
	            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
	                String referrer = request.getHeader("referer");
	                if (referrer == null) {
	                    referrer = request.getRequestURL().toString();
	                }
	                request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST",
	                    new SimpleSavedRequest(referrer));

	            }
	        };
	    }
	    
	    private ClientRegistration googleClientRegistration() {
			return CommonOAuth2Provider.GOOGLE.getBuilder("google")
				.clientId("google-client-id")
				.clientSecret("google-client-secret")
				.build();
		}
}
