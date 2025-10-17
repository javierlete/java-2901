const URL = '/citas/api/v2/citas/';

function App() {
	const [nuevaCita, setNuevaCita] = React.useState();

	return <>
		<h1>Citas</h1>
		<Listado nuevaCita={nuevaCita} />
		<Formulario setNuevaCita={setNuevaCita} />
	</>;
}

function Listado({nuevaCita}) {
	const [citas, setCitas] = React.useState([]);

	React.useEffect(() => {
		(async () => {
			const respuesta = await fetch(URL);
			const citas = await respuesta.json();

			setCitas(citas);
		})();
	}, [nuevaCita]); //[ { texto: 'Cita 1' }, { texto: 'Cita 2' }];

	return <>
		<h2>Listado</h2>

		<ul>
			{citas.map(c => <li key={c.texto}>{c.texto}</li>)}
		</ul>
	</>;
}

function Formulario({ setNuevaCita }) {
	const [cita, setCita] = React.useState({});

	function actualizarCita(campo, evento) {
		const citaNueva = { ...cita };
		citaNueva[campo] = evento.target.value;

		setCita(citaNueva);
	}

	async function guardar() {
		console.log(cita);

		const respuesta = await fetch(URL, {
			method: 'POST',
			body: JSON.stringify(cita),
			headers: { 'Content-type': 'application/json' }
		});

		const citaNueva = await respuesta.json();

		console.log(citaNueva);

		setNuevaCita(citaNueva);
	}

	return <>
		<h2>Formulario</h2>

		<p>{cita.texto}, {cita.inicio}, {cita.fin}</p>

		<form>
			<input placeholder="Texto" onInput={e => actualizarCita('texto', e)} />
			<input type="datetime-local" onInput={e => actualizarCita('inicio', e)} />
			<input type="datetime-local" onInput={e => actualizarCita('fin', e)} />

			<button type="button" onClick={guardar}>Guardar</button>
		</form>
	</>;
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
