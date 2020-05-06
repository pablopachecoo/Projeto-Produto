package com.b3.produto.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	//Configura a parte de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(//AutenticacaoService)
	
	}
	
	//Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/produtos").permitAll()
		.antMatchers(HttpMethod.GET, "/produtos/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
		
	}
	
	//Configurações de recursos estáticos(css, imagens, js, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
	
	
	}

}
