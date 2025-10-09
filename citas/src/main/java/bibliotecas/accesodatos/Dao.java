package bibliotecas.accesodatos;

import java.util.Optional;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	Optional<T> obtenerPorId(Long id);
	
	T insertar(T objeto);
	T modificar(T objeto);
	void borrar(Long id);
}
