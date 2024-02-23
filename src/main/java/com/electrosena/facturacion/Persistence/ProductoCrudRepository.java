package com.electrosena.facturacion.Persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electrosena.facturacion.Persistence.entities.Producto;

//La anotación @Repository indica que esta interfaz es un componente de repositorio de Spring 
//y permite que Spring la maneje como un bean.
@Repository
//La interfaz ProductoCrudRepository extiende JpaRepository, que proporciona métodos para realizar 
//operaciones CRUD en la entidad Producto.
//El primer parámetro de JpaRepository es el tipo de entidad con la que trabajará el repositorio (Producto) 
//y el segundo parámetro es el tipo del identificador de la entidad (String en este caso).
public interface ProductoCrudRepository extends JpaRepository<Producto, String> {

	// Métodos de consulta definidos por nombres convencionales de Spring Data JPA

	// Encuentra una lista de productos por marca, ignorando mayúsculas y
	// minúsculas.
	List<Producto> findByMarcaIgnoreCase(String marca);

	// Encuentra una lista de productos por marca, ignorando mayúsculas y
	// minúsculas, y los ordena por precio.
	List<Producto> findByMarcaIgnoreCaseContainingOrderByPrecio(String marca);

	// Verifica si existe un producto con el SKU dado, ignorando mayúsculas y
	// minúsculas.
	boolean existsBySkuIgnoreCase(String sku);

	// Encuentra un producto por SKU, ignorando mayúsculas y minúsculas.
	Optional<Producto> findBySkuIgnoreCase(String sku);

}
