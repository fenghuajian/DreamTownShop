package service;

import domain.Category;
import util.PageModel;

public interface ICategoryService {

	PageModel<Category> getAllCategory(int parseInt);


	void saveCategory(Category category);

	int deleteCategory(String categoryid);

    int updateCategory(String cname, String uname);
}
