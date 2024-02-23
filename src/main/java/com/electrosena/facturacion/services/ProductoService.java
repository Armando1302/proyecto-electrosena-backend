package com.electrosena.facturacion.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.electrosena.facturacion.Persistence.ProductoCrudRepository;
import com.electrosena.facturacion.Persistence.Dto.ProductoDto;
import com.electrosena.facturacion.Persistence.Dto.ProductoIdDto;
import com.electrosena.facturacion.Persistence.Dto.ProductoNuevoDto;
import com.electrosena.facturacion.Persistence.Dto.ProductoPrecioDto;
import com.electrosena.facturacion.Persistence.Dto.ResultadoDto;
import com.electrosena.facturacion.Persistence.entities.Producto;
import com.electrosena.facturacion.exception.ProductoNoEncontradoException;

//Se utiliza la anotacion @Service 
@Service
public class ProductoService {

	@Autowired
	ProductoCrudRepository productoCrudRepository;

	// Le damos un valor al dolar
	public int precioDolar = 4000;

	// Creamos un metodo que devuelve una lista
	public List<Producto> listarProductos() {
		return productoCrudRepository.findAll();
	}

	// Método para verificar si existe un producto con un SKU dado
	public boolean existeProductoConSku(String sku) {
	    // Buscar el producto por su SKU en la base de datos
	    Optional<Producto> productoOptional = productoCrudRepository.findBySkuIgnoreCase(sku);
	    // Devolver true si se encuentra el producto, false si no
	    return productoOptional.isPresent();
	}

	
	// Creamos un metodo que devuelve todos los productos, lo estoy usando en
	// angular
	public List<ProductoDto> listarTodosProductos() {
		List<Producto> listProductos = productoCrudRepository.findAll();
		List<ProductoDto> listaProductosDto = new ArrayList<>();
		for (Producto producto : listProductos) {
			ProductoDto productoDto = new ProductoDto(producto.getSku(), producto.getMarca(), producto.getPrecio(),
					producto.getModelo(), producto.getDescripcion(), producto.getPrecioVenta());
			listaProductosDto.add(productoDto);
		}
		return listaProductosDto;
	}

	// Metodo para obtener un producto por sku Angular
	public ResultadoDto obtenerProductoPorSku(String sku) {
		// Buscar el producto por su SKU en la base de datos
		Optional<Producto> productoOptional = productoCrudRepository.findBySkuIgnoreCase(sku);

		// Verificar si se encontró el producto
		if (productoOptional.isPresent()) {
			Producto productoEncontrado = productoOptional.get();
			ProductoDto productoDto = new ProductoDto();
			productoDto.setSku(productoEncontrado.getSku());
			productoDto.setDescripcion(productoEncontrado.getDescripcion());
			productoDto.setModelo(productoEncontrado.getModelo());
			productoDto.setMarca(productoEncontrado.getMarca());
			productoDto.setPrecio(productoEncontrado.getPrecio());
			productoDto.setPrecioVenta(productoEncontrado.getPrecioVenta());

			return new ResultadoDto("Producto encontrado", productoDto);
		} else {
			return new ResultadoDto("No se encontró ningún producto con el SKU: " + sku);
		}
	}

	// Creamos un nuevo método para almacenar Angular
	public ResultadoDto almacenarProductoV2(ProductoDto productoDto) {
	    // Verificar si ya existe un producto con el mismo SKU
	    if (productoCrudRepository.existsBySkuIgnoreCase(productoDto.getSku())) {
	        return new ResultadoDto("Error al almacenar producto. SKU duplicado: " + productoDto.getSku());
	    }

	    // Crear un nuevo producto y almacenarlo
	    Producto producto = new Producto();
	    producto.setSku(productoDto.getSku());
	    producto.setDescripcion(productoDto.getDescripcion());
	    producto.setModelo(productoDto.getModelo());
	    producto.setMarca(productoDto.getMarca());
	    producto.setPrecio(productoDto.getPrecio());
	    producto.setPrecioVenta(productoDto.getPrecioVenta());

	    Producto productoAlmacenado = productoCrudRepository.save(producto);

	    if (productoAlmacenado != null) {
	        return new ResultadoDto("Producto almacenado correctamente", productoAlmacenado.getSku());
	    } else {
	        return new ResultadoDto("Error al almacenar producto");
	    }
	}


	// Metodo para actualizar un producto angular
	public ResultadoDto actualizarProductoV2(ProductoDto productoDto) {
		// Validar la entrada (ejemplo: validar si el SKU es válido)
		if (!esSkuValido(productoDto.getSku())) {
			return new ResultadoDto("Error, SKU no válido");
		}

		// Buscar el producto en la base de datos
		Producto producto = productoCrudRepository.findBySkuIgnoreCase(productoDto.getSku()).orElse(null);

		// Verificar si el producto existe
		if (producto == null) {
			// Lanzar una excepción en lugar de devolver null
			throw new ProductoNoEncontradoException("El producto no existe");
		}

		// Actualizar el producto con la información proporcionada
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setModelo(productoDto.getModelo());
		producto.setMarca(productoDto.getMarca());
		producto.setPrecio(productoDto.getPrecio());
		producto.setPrecioVenta(productoDto.getPrecioVenta());

		// Guardar el producto actualizado
		productoCrudRepository.save(producto);

		// Enviar mensaje JSON de éxito
		return new ResultadoDto("Éxito, Producto actualizado correctamente");
	}

	
	// Metodo para eliminar productos Angular
	public ResultadoDto eliminarProductoV2(ProductoDto productoDto) {
	    // Validar la entrada (ejemplo: validar si el SKU es válido)
	    if (!esSkuValido(productoDto.getSku())) {
	        return new ResultadoDto("Error, SKU no válido");
	    }

	    // Buscar el producto en la base de datos
	    Producto producto = productoCrudRepository.findBySkuIgnoreCase(productoDto.getSku()).orElse(null);

	    // Verificar si el producto existe
	    if (producto == null) {
	        // Lanzar una excepción en lugar de devolver un mensaje de error
	        throw new ProductoNoEncontradoException("El producto no existe");
	    }

	    // El producto existe, eliminarlo.
	    productoCrudRepository.delete(producto);

	    // Devolver un mensaje JSON de éxito
	    return new ResultadoDto("Éxito, producto eliminado correctamente");
	}

	
	
	// Creamos un metodo que devuelve una lista de productos por marca
	public List<ProductoDto> listarProductosPorMarca(String marca) {
		List<Producto> listProductos = productoCrudRepository.findByMarcaIgnoreCase(marca);
		List<ProductoDto> listaProductosDto = new ArrayList<>();
		for (Producto producto : listProductos) {
			ProductoDto productoDto = new ProductoDto(producto.getSku(), producto.getMarca(), producto.getPrecio(),
					producto.getModelo(), producto.getDescripcion(), producto.getPrecioVenta());
			listaProductosDto.add(productoDto);
		}
		return listaProductosDto;

	}

	// Método para validar si el SKU es válido (ejemplo)
	private boolean esSkuValido(String sku) {
		// Implementación de la lógica de validación del SKU
		return sku != null && !sku.isEmpty(); // Ejemplo: SKU no puede ser nulo ni vacío
	}

	// Creamos un metodo pra listar productos por marca sin importar el orden
	public List<ProductoPrecioDto> listarPreciosProductosPorMarca(String marca) {
		List<Producto> listProductos = productoCrudRepository.findByMarcaIgnoreCaseContainingOrderByPrecio(marca);
		List<ProductoPrecioDto> listaProductosPrecioDto = new ArrayList<>();
		for (Producto producto : listProductos) {
			ProductoPrecioDto productoPrecioDto = new ProductoPrecioDto(producto.getSku(), producto.getMarca(),
					producto.getPrecio(), producto.getPrecio() * precioDolar);
			listaProductosPrecioDto.add(productoPrecioDto);
		}
		return listaProductosPrecioDto;

	}

	// Creamos un metodo para almacenar productos
	public ProductoNuevoDto almacenarProducto(ProductoNuevoDto productoNuevoDto) {
		Producto producto = new Producto();
		producto.setSku(productoNuevoDto.getSku());
		producto.setMarca(productoNuevoDto.getMarca());
		producto.setModelo(productoNuevoDto.getModelo());
		producto.setDescripcion(productoNuevoDto.getDescripcion());
		producto.setPrecio(productoNuevoDto.getPrecio());
		producto.setPrecioVenta(productoNuevoDto.getPrecioVenta());
		productoCrudRepository.save(producto);
		return productoNuevoDto;
	}

	/*
	 * public ResultadoDto almacenarProductoV2 ( ProductoNuevoDto productoNuevoDto )
	 * { Producto producto = new Producto();
	 * producto.setSku(productoNuevoDto.getSku());
	 * producto.setMarca(productoNuevoDto.getMarca());
	 * producto.setModelo(productoNuevoDto.getModelo());
	 * producto.setDescripcion(productoNuevoDto.getDescripcion());
	 * producto.setPrecio(productoNuevoDto.getPrecio());
	 * producto.setPrecioVenta(productoNuevoDto.getPrecioVenta()); Producto
	 * productoAlmacenado = productoCrudRepository.save(producto); if (
	 * productoAlmacenado!= null) { return new ResultadoDto
	 * (" Producto almacenado correctamente con codigo " +
	 * productoAlmacenado.getSku() ); } else { return new ResultadoDto
	 * (" Error al almacenar producto "); }
	 * 
	 * }
	 */

	// Creamos un metodo para actualizar productos
	/*
	 * public ProductoNuevoDto actualizarProducto (ProductoNuevoDto
	 * productoNuevoDto) { Producto producto =
	 * productoCrudRepository.findBySku(productoNuevoDto.getSku());
	 * producto.setSku(productoNuevoDto.getSku());
	 * producto.setMarca(productoNuevoDto.getMarca());
	 * producto.setModelo(productoNuevoDto.getModelo());
	 * producto.setDescripcion(productoNuevoDto.getDescripcion());
	 * producto.setPrecio(productoNuevoDto.getPrecio());
	 * producto.setPrecioVenta(productoNuevoDto.getPrecioVenta());
	 * productoCrudRepository.save(producto); return productoNuevoDto; }
	 */

	// Creamos un metodo para eliminar productos
	public void eliminarProducto(ProductoIdDto productoIdDto) {
		Producto producto = productoCrudRepository.getReferenceById(productoIdDto.getSku());
		productoCrudRepository.delete(producto);
	}

	

//Final 	
}
