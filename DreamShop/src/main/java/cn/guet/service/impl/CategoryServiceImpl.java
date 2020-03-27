package cn.guet.service.impl;


import cn.guet.dao.ICategoryDao;
import cn.guet.domain.Category;
import cn.guet.service.ICategoryService;
import cn.guet.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryService")
@Transactional()
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	ICategoryDao categoryDao;


	@Override
	public PageModel<Category> getAllCategory(int currentPage) {
		return null;
	}

	@Override
	public void saveCategory(Category category) {

	}

	@Override
	public int deleteCategory(String categoryid) {
		return 0;
	}
}