package com.ipartek.formacion.amazonia.pruebas;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.amazonia.entidades.Categoria;
import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.repositorios.CategoriaRepository;
import com.ipartek.formacion.amazonia.repositorios.ProductoRepository;

@Component
public class DatosFalsos implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		var informatica = Categoria.builder().nombre("Informática").build();
		var electronica = Categoria.builder().nombre("Electrónica").build();

		categoriaRepository.save(informatica);
		categoriaRepository.save(electronica);
		
		var portatil = Producto.builder().nombre("Portátil").precio(new BigDecimal("1234.56")).categoria(informatica).build();
		var monitor = Producto.builder().nombre("Monitor").precio(new BigDecimal("123.45")).categoria(informatica).build();
		var raton = Producto.builder().nombre("Ratón").precio(new BigDecimal("12.34")).categoria(informatica).build();
		var nevera = Producto.builder().nombre("Nevera").precio(new BigDecimal("234")).categoria(electronica).build();
		var tv = Producto.builder().nombre("TV").precio(new BigDecimal("354")).categoria(electronica).build();
		
		productoRepository.save(portatil);
		productoRepository.save(monitor);
		productoRepository.save(raton);
		productoRepository.save(nevera);
		productoRepository.save(tv);
	}

}
