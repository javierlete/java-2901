package com.ipartek.formacion.amazonia.sesion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.amazonia.entidades.Producto;

import lombok.Getter;

@Component
@SessionScope
public class Carrito {
	@Getter
	private Collection<Producto> productos = new ArrayList<>();

	public void agregarProducto(Producto producto) {
		productos.add(producto);
	}
	
	public BigDecimal getTotal() {
		return productos.stream().map(p -> p.getPrecio()).reduce((total, precio) -> precio.add(total))
				.orElse(BigDecimal.ZERO);
	}
}
