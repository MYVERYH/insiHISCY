package com.web.common;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, K extends Serializable> {
	
	public List<T> selectAll();

	public T findById(int id);

	public String insert(T t);

	public String update(T t);

	public String delete(int id);	
	
}
