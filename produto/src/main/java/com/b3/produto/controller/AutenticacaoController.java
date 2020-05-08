package com.b3.produto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b3.produto.controller.dto.TokenDto;
import com.b3.produto.controller.form.LoginForm; 
import com.b3.produto.config.security.TokenService;
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		System.out.println(form.getEmail());
		System.out.println(form.getSenha());
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin); // autentica os dados do login, o form com o email e senha
			String token = tokenService.gerarToken(authentication); // gera o token com os dados de login e já autenticados pelo authmanager
			System.out.println(token); 
			return ResponseEntity.ok(new TokenDto(token, "Bearer")); // retorna um json com o token e a estratégia
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
