package es.babel.curso.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import es.babel.curso.entity.Coche;
import es.babel.curso.repository.CocheRepository;

@Repository
public class CocheRepositoryImpl implements CocheRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * @param c -> Par�metro objeto Coche 
	 * @return -> Devuelve true en caso de que se haya podido completar el alta del coche
	 */
	@Override
	public boolean alta(Coche c) {
		try {
			em.persist(c);
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * @param id -> Par�metro con la id del coche que se quiere dar de baja
	 * @return -> Devuelve true en caso de que se haya podido completar la eliminaci�n del coche
	 */
	@Override
	public boolean baja(int id) {
		try {
			Coche c = em.find(Coche.class, id);
			em.remove(c);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	/**
	 * @param c -> Par�metro objeto Coche
	 * @return -> Devuelve true en caso de que se haya podido completar la modificaci�n del coche
	 */
	@Override
	public boolean modificar(Coche c) {
		try {
			em.merge(c);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * @param id -> Par�metro con la id del coche que se quiere obtener
	 * @return -> Devuelve true en caso de que se haya podido obtener el coche
	 */
	@Override
	public Coche obtener(int id) {
		return em.find(Coche.class, id);
	}

	/**
	 * @return -> Devuelve la lista de Coches existentes en la BBDD
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Coche> listar() {
		return em.createQuery("FROM Coche c").getResultList();
	}

}
