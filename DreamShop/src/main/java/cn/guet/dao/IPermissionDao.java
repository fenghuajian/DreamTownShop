package cn.guet.dao;



import cn.guet.domain.Permission;
import cn.guet.util.PageModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPermissionDao {
	/**
	 * 根据rolesid罗列所有权限名
	 * @param roleId
	 * @return
	 */
	@Select("SELECT p.* FROM permission p,rolespermission rp WHERE rp.permissionid=p.permissionid AND rp.rolesid=#{roleId}")
	List<Permission> getPermissionByRoleId(String roleId);

	/**
	 * 获取所有权限
	 * @return
	 */
	@Select("select * from permission")
	List<Permission> getAllPermission();

	/**
	 * 分页，1，首先获取所有行数
	 * 2、拿到所有东西并放入list
	 * 3、拿到分页数据
	 *
	 * @return
	 */
	@Select("SELECT * FROM (SELECT rownum rn,t.* FROM (SELECT * FROM  permission) t WHERE rownum<=#{endRow}) WHERE rn>=#{startRow}")
	List<Permission> findPage(@Param("startRow") int startRow, @Param("endRow") int endRow);
	@Select("select count(*) from permission")
	int getPermissionNum();
	PageModel<Permission> getAll(int currentPage);

	/**
	 * 保存权限
	 * private String permissionid;
	 * 	private String pid;
	 * 	private String name;
	 * 	private String icon;
	 * 	private String iconSkin;
	 * 	private String url;
	 * 	private String isParent;
	 * 	private String target;
	 * 	private String checked;//标记权限是否选中
	 * 	private String open;
	 * @param p
	 */
	@Insert("insert into permission(permissionid,pid,name,icon,iconskin,url,isparent,target,checked,open)" +
			" values('${permissionid}','${pid}','${name}','${icon}','${iconSkin}','${url}'," +
			"'${isParent}','${target}','${checked}','${open}')")

	void savePermission(Permission p);

	/**
	 * 更新权限信息
	 * @param p
	 */
	@Update("update permission set permissionid='${permissionid}',pid='${pid}',name='${name}',icon='${icon}',iconskin='${iconSkin}'," +
			"url='${url}',isparent='${isParent}',target='${target}',checked='${checked}',open='${open}' where" +
			" permissionid='${permissionid}'")
	void updatePermission(Permission p);

	/**
	 * 根据permissionid删除permission
	 * @param permissionid
	 * @return
	 */
	@Delete("delete from permission where permissionid=#{permissionid}")
	int deletePermission(String permissionid);



}