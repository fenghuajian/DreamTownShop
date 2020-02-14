package dao;

import bean.Category;

public interface ICategoryDao extends IBaseDao<Category> {

    void saveCategory(Category category);
}
