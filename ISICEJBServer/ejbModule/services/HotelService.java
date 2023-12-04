package services;

import java.util.List;

import dao.HotelIDao;
import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Hotel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "Laili")
public class HotelService implements HotelIDao,IDaoRemote<Hotel>{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Hotel create(Hotel o) {
		em.persist(o);
		return o;
	}

	@Override
	public boolean delete(Hotel o) {
		em.remove(o);
		return true;
	}

	@Override
	public Hotel update(Hotel o) {
		return em.merge(o);
	}

	@Override
	public Hotel findById(int id) {
		
		return em.find(Hotel.class, id);
	}

	@Override
	public List<Hotel> findAll() {
		Query query = em.createQuery("select v from Hotel v");
		return query.getResultList();
	}

}
