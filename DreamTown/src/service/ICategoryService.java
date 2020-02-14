package service;

import bean.Category;
import util.PageModel;

public interface ICategoryService {

	PageModel<Category> getAllCategory(int parseInt);


	void saveCategory(Category category);

	int deleteCategory(String categoryid);
}
