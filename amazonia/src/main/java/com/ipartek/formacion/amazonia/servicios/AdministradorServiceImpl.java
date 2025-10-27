package com.ipartek.formacion.amazonia.servicios;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.amazonia.entidades.Producto;

@Service
public class AdministradorServiceImpl extends AnonimoServiceImpl implements AdministradorService {

	@Override
	public Producto altaProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto modificacionProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void bajaProducto(Long id) {
		productoRepository.deleteById(id);
	}

}
