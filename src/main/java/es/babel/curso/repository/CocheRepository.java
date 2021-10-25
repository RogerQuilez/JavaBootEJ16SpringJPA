package es.babel.curso.repository;

import java.util.List;

import es.babel.curso.entity.Coche;

public interface CocheRepository {

	boolean alta(Coche c);
	boolean baja(int id);
	boolean modificar(Coche c);
	Coche obtener(int id);
	List<Coche> listar();
}
