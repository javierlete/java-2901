package com.ipartek.formacion.amazonia.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.amazonia.entidades.Producto;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface ProductoRepository extends CrudRepository<Producto, Long>, PagingAndSortingRepository<Producto, Long> {

	Iterable<Producto> findByCategoriaId(Long idCategoria);

}
