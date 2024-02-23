package com.electrosena.facturacion.exception;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.electrosena.facturacion.Persistence.Dto.ResultadoDto;

//La anotación @ControllerAdvice permite centralizar el manejo de excepciones para todos los controladores.
//Se especifica que este manejador se aplica solo a clases anotadas con @RestController.
@ControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	// Maneja todas las excepciones genéricas y devuelve una respuesta de error con
	// un mensaje detallado.
	// @ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		// List<String> details = new ArrayList<>();
		ResultadoDto errorDto = new ResultadoDto(ex.getMessage());
		return new ResponseEntity<Object>(errorDto, HttpStatus.BAD_REQUEST);
	}

	// Maneja la excepción ProductoNoEncontradoException y devuelve una respuesta de
	// error con un mensaje específico.
	@ExceptionHandler(ProductoNoEncontradoException.class)
	public ResponseEntity<Object> handleProductoNoEncontradoException(ProductoNoEncontradoException ex,
			WebRequest request) {
		ResultadoDto errorDto = new ResultadoDto("Producto no encontrado: " + ex.getMessage());
		return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(value = HttpMessageNotReadableException.class) public
	 * ResponseEntity<Object>
	 * handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
	 * String errorMessage = "Error de lectura del mensaje JSON: " +
	 * ex.getMessage(); ResultadoDto errorDto = new ResultadoDto (errorMessage);
	 * return new ResponseEntity<Object>( errorDto, HttpStatus.BAD_REQUEST); }
	 */

}
