package es.babel.curso.service;

import java.util.HashMap;
import java.util.List;

import es.babel.curso.entity.Coche;

public interface CocheService {
		
		public HashMap<String, String> alta(Coche c);
		public HashMap<String, String> baja(int id);
		public HashMap<String, String> modificar(Coche c);
		public Coche obtener(int id);
		public List<Coche> listar();
}
