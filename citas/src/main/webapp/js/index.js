'use strict';

const URL = 'api/v1/citas';

window.addEventListener('DOMContentLoaded', async () => {
	document.querySelector('button').addEventListener('click', guardar);
	await cargarCitas();
});

async function cargarCitas() {
	const respuesta = await fetch(URL);
	const citas = await respuesta.json();

	console.log(citas);

	const ul = document.querySelector('ul');

	ul.innerHTML = '';

	citas.forEach(cita => {
		const li = document.createElement('li');
		li.innerHTML = `
			<a href="javascript:detalle(${cita.id})">
				${cita.texto}
			</a>: 
			${cita.inicio} a ${cita.fin}
			<a href="javascript:editar(${cita.id})">Editar</a>
			<a href="javascript:borrar(${cita.id})">Borrar</a>
		`;

		ul.appendChild(li);
	});
}

async function detalle(id) {
	console.log('detalle', id);

	const h2 = document.querySelector('h2');
	const p = document.querySelector('p');

	const respuesta = await fetch(`${URL}/${id}`);
	const cita = await respuesta.json();

	h2.innerText = cita.texto;
	p.innerText = `${cita.inicio} a ${cita.fin}`;
}

async function borrar(id) {
	console.log('borrar', id);

	const respuesta = await fetch(`${URL}/${id}`, { method: 'DELETE' });

	console.log(respuesta);

	await cargarCitas();
}

async function guardar() {
	console.log('guardar');

	const f = document.forms[0];

	const cita = { texto: f.texto.value, inicio: f.inicio.value, fin: f.fin.value };

	console.log(cita);

	let respuesta;
	
	if (f.idCita.value) {
		cita.id = f.idCita.value;
		
		respuesta = await fetch(`${URL}/${cita.id}`, {
			method: 'PUT',
			body: JSON.stringify(cita),
		});
	} else {
		respuesta = await fetch(URL, {
			method: 'POST',
			body: JSON.stringify(cita),
		});
	}

	console.log(respuesta);

	f.reset();
	f.idCita.value = null;

	await cargarCitas();
}

async function editar(id) {
	console.log('editar', id);

	const respuesta = await fetch(`${URL}/${id}`);
	const cita = await respuesta.json();

	const f = document.forms[0];

	f.idCita.value = cita.id;
	f.texto.value = cita.texto;
	f.inicio.value = cita.inicio;
	f.fin.value = cita.fin;
}