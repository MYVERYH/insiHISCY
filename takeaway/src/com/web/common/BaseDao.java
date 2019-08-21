package com.web.common;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, K extends Serializable> {

	public List<T> selectAll();

	public T findById(int id);

	public int insert(T t);

	public int update(T t);

	public int delete(int id);

}
