package dao;

import domain.Roles;

public interface IRoleDao extends IBaseDao<Roles>{

    void saveGrant(String roleId, String permissionIds[]);
}
