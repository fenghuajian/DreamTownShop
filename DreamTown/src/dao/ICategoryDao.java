package dao;

import domain.Category;

public interface ICategoryDao extends IBaseDao<Category> {

    void saveCategory(Category category);
}
