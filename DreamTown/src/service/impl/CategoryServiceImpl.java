package service.impl;

import domain.Category;
import dao.ICategoryDao;
import dao.imp.CategoryDaoImpl;
import service.ICategoryService;
import util.PageModel;

public class CategoryServiceImpl implements ICategoryService {


	ICategoryDao categoryDao=null;
	public  CategoryServiceImpl(){
		categoryDao=new CategoryDaoImpl();
	}


	@Override
	public PageModel<Category> getAllCategory(int currentPage) {
		return categoryDao.selectAll(currentPage);
	}

	@Override
	public void saveCategory(Category category) {
		categoryDao.save(category);
	}

	@Override
	public int deleteCategory(String categoryid) {
		return categoryDao.delete(categoryid);
	}
}