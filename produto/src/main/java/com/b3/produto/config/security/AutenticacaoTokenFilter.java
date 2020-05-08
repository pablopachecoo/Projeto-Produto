package com.b3.produto.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.b3.produto.Repository.UsuarioRepository;
import com.b3.produto.model.Usuario;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenService.isValido(token);
		if (valido) {
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);
		
	}

	private void autenticarCliente(String token) { // dentro do token tem o Id do usuário
		Long usuarioId = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(usuarioId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}


	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization"); //Pega o Header "Authorization" da request
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) { // se o token for nulo/vazio/ou não começar com Bearer 
			return null;
		}
		return token.substring(7, token.length()); 
	}

}
