package com.mark.data;

import java.util.List;

public interface DataAccessInterface <T> {
	public List<T> findAll();
	public T findByID(int id);
	public T findBy(T t);
	public boolean create(T album);
	public boolean update(T t);
	public boolean delete(T t);
}