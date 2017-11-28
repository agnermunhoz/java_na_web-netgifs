package br.com.fiap.netgifs.core;

import java.util.List;

public interface HelperInterface<T> {

	public T saveOrUpdate(T reg);
	public boolean delete(T reg);
	public T find(int id);
	public List<T> list();
}
