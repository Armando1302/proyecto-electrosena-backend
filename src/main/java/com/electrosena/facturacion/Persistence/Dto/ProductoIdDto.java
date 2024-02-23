package com.electrosena.facturacion.Persistence.Dto;

// La clase ProductoIdDto es un DTO utilizado para transportar el identificador de un producto.
public class ProductoIdDto {

	// Atributo que representa el SKU del producto.
	private String sku;

	// Método getter para obtener el SKU del producto.
	public String getSku() {
		return sku;
	}

	// Método setter para establecer el SKU del producto.
	public void setSku(String sku) {
		this.sku = sku;
	}
}
