package com.electrosena.facturacion.Persistence.Dto;

// La clase ResultadoDto es un DTO utilizado para transportar resultados de operaciones, como mensajes y SKU.
public class ResultadoDto {

	// Atributos que representan el mensaje y el SKU.
	private String mensaje;
	private String sku;

	// Constructor que acepta un mensaje y un SKU.
	public ResultadoDto(String mensaje, String sku) {
		this.mensaje = mensaje;
		this.sku = sku;
	}

	// Constructor que acepta solo un mensaje.
	public ResultadoDto(String mensaje) {
		this.mensaje = mensaje;
	}

	public ResultadoDto(String mensaje2, ProductoDto productoDto) {
		// TODO Auto-generated constructor stub
	}

	// Métodos getter y setter para acceder y modificar el mensaje.
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	// Métodos getter y setter para acceder y modificar el SKU.
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}
