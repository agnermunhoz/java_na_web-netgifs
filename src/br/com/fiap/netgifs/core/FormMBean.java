package br.com.fiap.netgifs.core;

import java.util.List;

public interface FormMBean<T> {
	
	public List<T> getEntities();
	public T getEntity();
	public void setEntity(T entity);
	public void selectEntity();
	public void newEntity();
	public void saveEntity();
	public void deleteEntity();

}
