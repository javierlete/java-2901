package com.ipartek.formacion.springcitas.repositorios;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.springcitas.entidades.Cita;

@RepositoryRestResource(collectionResourceRel = "citas", path = "citas")
public interface CitaRepository extends CrudRepository<Cita, Long>, PagingAndSortingRepository<Cita, Long> {
	Page<Cita> findByInicioBetween(Pageable pageable, LocalDateTime inicio, LocalDateTime fin);
}
