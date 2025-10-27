package com.ipartek.formacion.amazonia.servicios;

import com.ipartek.formacion.amazonia.entidades.Categoria;
import com.ipartek.formacion.amazonia.entidades.Producto;

public interface AnonimoService {
	Iterable<Producto> listadoProductos();
	Iterable<Producto> listadoProductos(Long idCategoria);
	Iterable<Categoria> listadoCategorias();
	Producto detalleProducto(Long id);
}
