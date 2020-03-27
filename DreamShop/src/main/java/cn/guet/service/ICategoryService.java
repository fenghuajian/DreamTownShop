package cn.guet.service;


import cn.guet.domain.Category;
import cn.guet.util.PageModel;

public interface ICategoryService {

	PageModel<Category> getAllCategory(int parseInt);


	void saveCategory(Category category);

	int deleteCategory(String categoryid);
}
