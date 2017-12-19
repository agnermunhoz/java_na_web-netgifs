package br.com.fiap.netgifs.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.netgifs.core.HelperInterface;
import br.com.fiap.netgifs.listener.LocalEntityManagerFactory;

public class GenericaDAO<T> implements HelperInterface<T> {

	private EntityManager em;
	private Class<T> type;
	
	public GenericaDAO(Class<T> type) {
		this.em = LocalEntityManagerFactory.createEntityManager();
		this.type = type;
	}

	@Override
	public boolean save(T reg) {
		try {
			em.getTransaction().begin();
			em.persist(reg);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(T reg) {
		try {
			em.getTransaction().begin();
			em.merge(reg);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean saveOrUpdate(T reg) {
		try {
			em.getTransaction().begin();
			em.persist(reg);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(T reg) {
		try {
			em.getTransaction().begin();
			em.remove(reg);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public T find(int id) {
		try {
			T reg = em.find(type, id);

			return reg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> list() {
		try {
			Query query = em.createQuery("select e from "+type.getName()+" e"); 
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public T find(String jpql, Object... params) {
		try {
			Query query = em.createQuery(jpql);
			for(int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
			return (T) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<T> list(String jpql, Object... params) {
		try {
			Query query = em.createQuery(jpql);
			for(int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}