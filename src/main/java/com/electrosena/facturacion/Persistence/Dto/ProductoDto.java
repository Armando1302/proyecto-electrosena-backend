package com.electrosena.facturacion.Persistence.Dto;

public class ProductoDto {
	
	// Atributos de la clase
    private String sku; // Código SKU del producto
    private String marca; // Marca del producto
    private Integer precio; // Precio de compra del producto
    private String modelo; // Modelo del producto
    private String descripcion; // Descripción del producto
    private Integer precioVenta; // Precio de venta del producto

    // Métodos Getters y Setters para acceder y modificar los atributos

    // Método getter para obtener el SKU del producto
    public String getSku() {
        return sku;
    }

    // Método setter para establecer el SKU del producto
    public void setSku(String sku) {
        this.sku = sku;
    }

    // Método getter para obtener la marca del producto
    public String getMarca() {
        return marca;
    }

    // Método setter para establecer la marca del producto
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Método getter para obtener el precio del producto
    public Integer getPrecio() {
        return precio;
    }

    // Método setter para establecer el precio del producto
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    // Método getter para obtener el modelo del producto
    public String getModelo() {
        return modelo;
    }

    // Método setter para establecer el modelo del producto
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Método getter para obtener la descripción del producto
    public String getDescripcion() {
        return descripcion;
    }

    // Método setter para establecer la descripción del producto
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método getter para obtener el precio de venta del producto
    public Integer getPrecioVenta() {
        return precioVenta;
    }

    // Método setter para establecer el precio de venta del producto
    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    // Constructor de la clase ProductoNuevoDto
    /**
     * Constructor para crear un nuevo ProductoNuevoDto
     * @param sku           Código SKU del producto
     * @param marca         Marca del producto
     * @param precio        Precio de compra del producto
     * @param modelo        Modelo del producto
     * @param descripcion   Descripción del producto
     * @param precioVenta   Precio de venta del producto
     */
    public ProductoDto(String sku, String marca, Integer precio, String modelo, String descripcion,
            Integer precioVenta) {
        super();
        this.sku = sku;
        this.marca = marca;
        this.precio = precio;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
    }

	public ProductoDto() {
		// TODO Auto-generated constructor stub
	}
    
   
    
    
    
}
