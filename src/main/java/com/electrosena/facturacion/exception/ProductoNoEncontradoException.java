package com.electrosena.facturacion.exception;

//La clase ProductoNoEncontradoException representa una excepción personalizada 
//que se lanza cuando un producto no puede ser encontrado.
public class ProductoNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// Constructor que toma un mensaje como argumento para proporcionar información
	// sobre la excepción.
	public ProductoNoEncontradoException(String message) {
		// Llama al constructor de la clase base (RuntimeException) con el mensaje
		// proporcionado.
		super(message);
	}
}
