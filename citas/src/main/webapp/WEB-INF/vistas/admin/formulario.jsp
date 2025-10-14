<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<c:if test="${error != null}">
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		${error}
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
</c:if>

<form action="admin/guardar" method="post" class="needs-validationn"
	novalidate>
	<input type="hidden" name="id" value="${cita.id}">
	<div class="row mb-3">
		<label for="texto" class="col-sm-2 col-form-label">Texto</label>
		<div class="col-sm-10">
			<input type="text" required
				class="form-control ${errores.texto != null ? 'is-invalid' : '' }"
				id="texto" name="texto" value="${cita.texto}">
			<div class="invalid-feedback">${errores.texto != null ? errores.texto : 'No se permiten textos vacíos'}
			</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="inicio" class="col-sm-2 col-form-label">Fecha
			inicio</label>
		<div class="col-sm-10">
			<input type="datetime-local" required
				class="form-control ${errores.inicio != null ? 'is-invalid': '' }"
				id="inicio" name="inicio" value="${cita.inicio}">
			<div class="invalid-feedback">${errores.inicio != null ? errores.inicio : 'No se permiten fechas de inicio vacías' }
			</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="fin" class="col-sm-2 col-form-label">Fecha fin</label>
		<div class="col-sm-10">
			<input type="datetime-local" required
				class="form-control ${errores.fin != null ? 'is-invalid' : '' }"
				id="fin" name="fin" value="${cita.fin}">
			<div class="invalid-feedback">${errores.fin != null ? errores.fin : 'No se permiten fechas de fin vacías' }
			</div>
		</div>
	</div>
	<div class="row mb-3">
		<div class="offset-sm-2 col-sm-10">
			<button type="submit" class="btn btn-primary">Guardar</button>
			<div class="text-danger">
				${errores['']}
			</div>
		</div>
	</div>

</form>

<script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
</script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
