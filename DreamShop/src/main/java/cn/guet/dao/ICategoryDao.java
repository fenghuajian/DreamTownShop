package cn.guet.dao;


import cn.guet.domain.Category;
import cn.guet.util.PageModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryDao {

    /**
     * 保存类别
     * @param category
     */
    @Insert("INSERT INTO category(categoryid,name) values(#{categoryid},#{name})")
    void saveCategory(Category category);

    /**
     * 分页，首先拿到Num,
     * 再拿到所有数据放进list
     * 拿到分页所需数据
     *
     * @return
     */
    @Select("SELECT count(*) from category")
    int getNum();
    @Select("SELECT * FROM CATEGORY")
    List<Category> getAll();
    @Select("SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM  CATEGORY) t WHERE rownum<=#{endRow}) WHERE rn>=#{startRow}")
    List<Category> findPage(@Param("startRow") int startRow, @Param("endRow") int endRow);

    PageModel<Category> getAllCategory(int parseInt);

    /**
     * 根据id删除
     * @param categoryid
     * @return
     */
    @Delete("delete from category where categoryid=#{categoryid}")
    int deleteCategory(String categoryid);
}
