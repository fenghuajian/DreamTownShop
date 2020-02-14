package dao;

import util.PageModel;

import java.util.List;

public interface IBaseDao<T> {
	void save(T t);
	<O> T getById(O id);//使用泛型方法
	PageModel<T> selectAll(int currentPage);
	int getTotalRows();
	List<T> getAll();//获取所有，但是不分页
	<O> int delete(O id);
	void update(T t);
}