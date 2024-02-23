package com.electrosena.facturacion.Persistence.Dto;

// La clase ProductoPrecioDto es un DTO utilizado para transportar información sobre un producto junto con su precio en dólares y pesos.
public class ProductoPrecioDto {

	// Atributos que representan el código, la marca y los precios del producto en
	// dólares y pesos.
	private String codigo;
	private String marca;
	private Integer precioDolares;
	private Integer precioPesos;

	// Constructor que inicializa los atributos del DTO con los valores
	// proporcionados.
	public ProductoPrecioDto(String codigo, String marca, Integer precioDolares, Integer precioPesos) {
		this.codigo = codigo;
		this.marca = marca;
		this.precioDolares = precioDolares;
		this.precioPesos = precioPesos;
	}

	// Métodos getter y setter para acceder y modificar los atributos del DTO.

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getPrecioDolares() {
		return precioDolares;
	}

	public void setPrecioDolares(Integer precioDolares) {
		this.precioDolares = precioDolares;
	}

	public Integer getPrecioPesos() {
		return precioPesos;
	}

	public void setPrecioPesos(Integer precioPesos) {
		this.precioPesos = precioPesos;
	}
}
