package es.babel.curso.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.babel.curso.entity.Coche;
import es.babel.curso.repository.CocheRepository;
import es.babel.curso.service.CocheService;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheRepository cocheRepository;

	/**
	 * @param c -> Objeto coche
	 * @return -> Devuelve un objeto HashMap indicando si est� todo correcto o han habido errores
	 */
	@Override
	@Transactional
	public HashMap<String, String> alta(Coche c) {
		return altaAndModificar(c, "Alta");
	}

	/**
	 * @param id -> par�metro para buscar a trav�s del repositorio un objeto Coche con esa ID
	 * @return -> Devuelve un objeto HashMap indicando si est� todo correcto o han habido errores
	 */
	@Override
	@Transactional
	public HashMap<String, String> baja(int id) {
		HashMap<String, String> messages = new HashMap<>();
		Coche coche = cocheRepository.obtener(id);
		if (coche != null) {			
			if (cocheRepository.baja(id))
				messages.put("Baja Correcta -> ", "El vehiculo se ha eliminado correctamente");
			else
				messages.put("Persistence Error -> ", "Error contra la base de datos");
		} else {
			messages.put("Null Error -> ", "El coche con la ID " + id + " no se encuentra en la bbdd");
		}
		return messages;
	}

	/**
	 * @param c -> Objeto coche
	 * @return -> Devuelve un objeto HashMap indicando si est� todo correcto o han habido errores
	 */
	@Override
	@Transactional
	public HashMap<String, String> modificar(Coche c) {
		return altaAndModificar(c, "Modificar");
	}

	/**
	 * @param id -> par�metro para buscar a trav�s del repositorio un objeto Coche con esa ID
	 * @return -> Devuelve el objeto Coche encontrado con esa ID
	 */
	@Override
	public Coche obtener(int id) {
		return cocheRepository.obtener(id);
	}

	/**
	 * @return -> Devuelve la lista de los coches existentes en la BBDD
	 */
	@Override
	public List<Coche> listar() {
		return cocheRepository.listar();
	}
	
	/**
	 * @param c -> Objeto coche
	 * @param message -> Mensaje indicando si es Alta o Modificaci�n
	 * @return -> Devuelve un objeto HashMap indicando si est� todo correcto o han habido errores
	 */
	@Transactional
	public HashMap<String, String> altaAndModificar(Coche c, String message) {
		
		HashMap<String, String> messages = new HashMap<>();
		if (c != null) {
			if (c.getMarca().length() == 0) {
				messages.put("Marca Error -> ", "El campo Marca no puede estar vac�o");
			} 
			if (c.getMatricula().length() != 7) {
				messages.put("Matricula Error -> ", "El campo Matricula debe tener 7 caracteres");
			}
			if (c.getModel().length() == 0) {
				messages.put("Model Error -> ", "El campo Modelo no puede estar vac�o");
			}
			if (messages.isEmpty()) {
				if (this.cocheRepository.alta(c))
					messages.put(message +" Correcta -> ", "El vehiculo ha sido creado correctamente");
				else 
					messages.put("Persistence Error -> ", "Error contra la base de datos");
			}
		} else {
			messages.put("Null Error -> ", "El objeto coche no puede ser nulo");
		}
		return messages;
	}
	
}
