package com.ipartek.formacion.amazonia.servicios;

import com.ipartek.formacion.amazonia.entidades.Producto;

public interface AdministradorService extends AnonimoService {
	Producto altaProducto(Producto producto);
	Producto modificacionProducto(Producto producto);
	void bajaProducto(Long id);
}
