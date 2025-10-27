package com.ipartek.formacion.amazonia.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.amazonia.entidades.Categoria;
import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.repositorios.CategoriaRepository;
import com.ipartek.formacion.amazonia.repositorios.ProductoRepository;

@Service
@Primary
public class AnonimoServiceImpl implements AnonimoService {

	@Autowired
	protected ProductoRepository productoRepository;

	@Autowired
	protected CategoriaRepository categoriaRepository;
	
	@Override
	public Iterable<Producto> listadoProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Iterable<Producto> listadoProductos(Long idCategoria) {
		if(idCategoria == null) {
			return listadoProductos();
		}
		
		return productoRepository.findByCategoriaId(idCategoria);
	}

	@Override
	public Iterable<Categoria> listadoCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	public Producto detalleProducto(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

}
