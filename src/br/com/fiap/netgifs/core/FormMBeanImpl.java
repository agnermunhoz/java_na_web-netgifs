package br.com.fiap.netgifs.core;

import java.util.List;

import br.com.fiap.netgifs.helper.GenericaDAO;

public abstract class FormMBeanImpl<T> implements FormMBean<T> {
	
	private List<T> entities;
	private T entity;
	private GenericaDAO<T> dao = createDao();

	protected abstract GenericaDAO<T> createDao();
	protected abstract T createEntity();
	
	@Override
	public List<T> getEntities() {
		if (entities == null)
			entities = dao.list();
		return entities;
	}

	@Override
	public T getEntity() {
		if (entity == null)
			entity = createEntity();
		return entity;
	}

	@Override
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	@Override
	public void selectEntity() {
		System.out.println("Entity "+entity.toString()+" selected.");
	}
	
	@Override
	public void newEntity() {
		entity = createEntity();
	}

	@Override
	public void saveEntity() {
		dao.saveOrUpdate(entity);
		if (!entities.contains(entity)) {
			entities.add(entity);
		}
		newEntity();	
	}

	@Override
	public void deleteEntity() {
		entities.remove(entity);
		dao.delete(entity);
		newEntity();
	}

	
}
