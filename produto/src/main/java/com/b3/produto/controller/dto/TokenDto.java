package com.b3.produto.controller.dto;

public class TokenDto {

	public String token;
	public String tipo;

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getString() {
		return tipo;
	}

}
