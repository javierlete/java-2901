package com.ipartek.formacion.amazonia.controladores.advices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.amazonia.servicios.AnonimoService;

@ControllerAdvice
public class ComunControllerAdvice {
	@Autowired
	private AnonimoService anonimoService;
	
	@ModelAttribute
	private void modeloGlobal(Model modelo) {
		modelo.addAttribute("categorias", anonimoService.listadoCategorias());
	}
}
