package com.electrosena.facturacion;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.electrosena.facturacion.services.Calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FacturacionApplicationTests {

	@Test
	void contextLoads() {
		
	}
	
	@Test
	public void testSumar() {
		Calculadora calculadora = new Calculadora();
		int resultado = calculadora.sumar(3, 5);
		assertEquals(8, resultado);
	}
	
	@Test
	public void testPotencia() {
		Calculadora calculadora = new Calculadora();
		double resultado = calculadora.potencia(2, 5);
		double resultadoEsperado = 32;
		assertEquals(resultadoEsperado, resultado);
	}
	
	
	
	
	
	
}
