package com.b3.produto.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) // validando post
	public List<ErroNaValidacaoDto> handle(MethodArgumentNotValidException e) {
		List<ErroNaValidacaoDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.forEach(i -> {
			String mensagem = messageSource.getMessage(i, LocaleContextHolder.getLocale());
			ErroNaValidacaoDto erro = new ErroNaValidacaoDto(i.getField(), mensagem);
			dto.add(erro);
		});
		return dto;
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDataAccessApiUsageException.class) //InvalidDataAccessApiUsageException
	public List<ErroNaValidacaoDto> handle(InvalidDataAccessApiUsageException e) {
		List<ErroNaValidacaoDto> dto = new ArrayList<>();
		//List<FieldError> fieldErrors =
		ErroNaValidacaoDto erro = new ErroNaValidacaoDto(/*Pegar o nome do Campo*/e.getMessage()," não pode ser vazio");
		dto.add(erro);
		return dto;
	}
	
}