package com.electrosena.facturacion.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electrosena.facturacion.Persistence.entities.Cliente;

//La anotación @Repository indica que esta interfaz es un componente de repositorio de Spring 
//y permite que Spring la maneje como un bean.
@Repository
//La interfaz ClienteCrudRepository extiende JpaRepository, que proporciona métodos para 
//realizar operaciones CRUD en la entidad Cliente.
//El primer parámetro de JpaRepository es el tipo de entidad con la que trabajará el repositorio (Cliente) 
//y el segundo parámetro es el tipo del identificador de la entidad (String en este caso).
public interface ClienteCrudRepository extends JpaRepository<Cliente, String> {

}
