package bean;

public class RolesPermission {
    private String rolesid;
    private String permissionid;

    @Override
    public String toString() {
        return "RolesPermission{" +
                "rolesid='" + rolesid + '\'' +
                ", permissionid='" + permissionid + '\'' +
                '}';
    }

    public String getRolesid() {
        return rolesid;
    }

    public void setRolesid(String rolesid) {
        this.rolesid = rolesid;
    }

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid;
    }
}
