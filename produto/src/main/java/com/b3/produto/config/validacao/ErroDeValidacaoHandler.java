package com.b3.produto.config.validacao;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentNotValidException.class, InvalidDataAccessApiUsageException.class,
			TransientPropertyValueException.class, EmptyResultDataAccessException.class}) // Pega Campos Nulos e Vazios
	public List<ErroDeCampo> handle(MethodArgumentNotValidException e) {
		
		List<ErroDeCampo> dto = new ArrayList<>();
		// List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		ErroDeCampo erro = new ErroDeCampo(e.getLocalizedMessage());
		dto.add(erro);

		return dto;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void tratar() {
		
	}
	
}
