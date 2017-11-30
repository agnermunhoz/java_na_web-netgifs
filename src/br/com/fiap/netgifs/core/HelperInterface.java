package br.com.fiap.netgifs.core;

import java.util.List;

public interface HelperInterface<T> {

	public boolean saveOrUpdate(T reg);
	public boolean delete(T reg);
	public T find(int id);
	public T find(String jpql, Object... params);
	public List<T> list();
	public List<T> list(String jpql, Object... params);
}
