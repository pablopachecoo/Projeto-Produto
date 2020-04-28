package com.b3.produto.config.validacao;

public class ErroNaValidacaoDto {

	private String campo;
	private String erro;

	public ErroNaValidacaoDto(String campo, String erro) {
		this.campo= campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
