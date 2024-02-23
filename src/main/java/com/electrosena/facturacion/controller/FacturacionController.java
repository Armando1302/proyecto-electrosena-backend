package com.electrosena.facturacion.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.electrosena.facturacion.Persistence.Dto.ProductoDto;
import com.electrosena.facturacion.Persistence.Dto.ProductoIdDto;
import com.electrosena.facturacion.Persistence.Dto.ProductoNuevoDto;
import com.electrosena.facturacion.Persistence.Dto.ProductoPrecioDto;
import com.electrosena.facturacion.Persistence.Dto.ResultadoDto;
import com.electrosena.facturacion.exception.ProductoNoEncontradoException;
import com.electrosena.facturacion.services.ProductoService;

@RestController
@RequestMapping("/api/facturacion")
@CrossOrigin(origins = "http://localhost:4200/")
public class FacturacionController {

	@Autowired
	ProductoService productoService;

	// Endpoint para saludar y mostrar la cantidad de productos en venta
	@GetMapping("/saludar")
	public String saludo() {
		return "Hola mundo, existen " + productoService.listarProductos().size()
				+ " productos a la venta en electrosena";
	}

	// Endpoint para listar todos los productos (angular)
	@GetMapping("/listarProductos")
	public List<ProductoDto> listarTodosProductos() {
		List<ProductoDto> listaProductos = productoService.listarTodosProductos();
		return listaProductos;

	}

	// Endpoint para verificar si un SKU existe
	@GetMapping("/verificarSku/{sku}")
	public boolean verificarSku(@PathVariable String sku) {
		return productoService.existeProductoConSku(sku);
	}

	// Endpoint para obtener un producto por su SKU (angular)
	@GetMapping("/productos/{sku}")
	public ResultadoDto obtenerProductoPorSku(@PathVariable String sku) {
		return productoService.obtenerProductoPorSku(sku);
	}

	// Endpoint para almacenar un producto con ResponseEntity (utilizado en Angular)
	@PostMapping("/listarProductos")
	public ResponseEntity<ResultadoDto> almacenarProductoV2(@RequestBody ProductoDto productoDto) {
		if (productoDto == null) {
			return ResponseEntity.badRequest().body(new ResultadoDto("La solicitud no puede ser nula."));
		}

		try {
			ResultadoDto resultado = productoService.almacenarProductoV2(productoDto);
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			// Manejo de excepciones específicas si es necesario
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultadoDto("Error interno al procesar la solicitud."));
		}
	}

	// Endpoint para actualizar un producto (utilizado en Angular)
	@PutMapping("/productos/{sku}")
	public ResponseEntity<ResultadoDto> actualizarProductoById(@PathVariable String sku,
			@RequestBody ProductoDto productoDto) {
		if (!sku.equals(productoDto.getSku())) {
			return ResponseEntity.badRequest()
					.body(new ResultadoDto("El SKU en la URL no coincide con el SKU en el cuerpo de la solicitud."));
		}

		try {
			ResultadoDto resultado = productoService.actualizarProductoV2(productoDto);
			return ResponseEntity.ok(resultado);
		} catch (ProductoNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultadoDto("Producto no encontrado."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResultadoDto("Error interno al procesar la solicitud."));
		}
	}

	// Endpoint para eliminar un producto (utilizado en Angular)
	@DeleteMapping("/productos/{sku}")
	public ResponseEntity<ResultadoDto> eliminarProductoV2(@PathVariable String sku) {
		// Crea un ProductoDto y establece el SKU
		ProductoDto productoDto = new ProductoDto();
		productoDto.setSku(sku);

		// Llama al método eliminarProductoV2 del servicio con el ProductoDto
		ResultadoDto resultadoDto = productoService.eliminarProductoV2(productoDto);

		// Devuelve una respuesta con el resultado y el estado HTTP adecuado
		return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
	}

	// Endpoint para listar precios de productos por marca
	@GetMapping("/listarPreciosProductosPorMarca/{marca}")
	public List<ProductoPrecioDto> listarPreciosProductosPorMarca(@PathVariable String marca) {
		List<ProductoPrecioDto> listaProductos = productoService.listarPreciosProductosPorMarca(marca);
		return listaProductos;

	}

	// Endpoint para almacenar un nuevo producto
	@PostMapping("/almacenarProducto")
	public ProductoNuevoDto almacenarProducto(@RequestBody ProductoNuevoDto productoNuevoDto) {
		return productoService.almacenarProducto(productoNuevoDto);
	}

	// Funcion para actualizar (Put)
	/*
	 * @PutMapping ("/actualizarProducto") public ProductoNuevoDto
	 * actualizarProducto ( @RequestBody ProductoNuevoDto productoNuevoDto) { return
	 * productoService.actualizarProducto(productoNuevoDto); }
	 */

	// Endpoint para eliminar un producto por su ID
	@DeleteMapping("/eliminarProductoById")
	public void eliminarProductoById(@RequestBody ProductoIdDto productoIdDto) {
		productoService.eliminarProducto(productoIdDto);

	}

//Final		
}
