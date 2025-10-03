package com.ipartek.formacion.citas.presentacion.controladores;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.accesodatos.DaoUsuario;

public class Globales {
	public static final DaoCita DAO_CITA = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	public static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");
}
