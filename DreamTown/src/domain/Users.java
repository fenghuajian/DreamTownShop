package domain;

public class Users {

	private String usersId;
	private String username;
	private String password;
	private String rolesId;
	private Roles role;//Hibernate��Mybatis��ֻ�Ǳ���Ȩ����


	@Override

	public String toString() {
		return "Users{" +
				"usersId='" + usersId + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", rolesId='" + rolesId + '\'' +
				", role=" + role +
				'}';
	}

	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRolesId() {
		return rolesId;
	}
	public void setRolesId(String rolesId) {
		this.rolesId = rolesId;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
}