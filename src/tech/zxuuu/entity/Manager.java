package tech.zxuuu.entity;

/**
 * 各类管理员实体类
 * @author z0gSh1u
 */

public class Manager {

	private String username; // 用户名
	private String password; // 密码（MD5）
	/**
	 * @see ManagerType
	 */
	private ManagerType managerType; // 管理员类型
	
	public Manager(String username, String password, ManagerType managerType) {
		super();
		this.username = username;
		this.password = password;
		this.managerType = managerType;
	}

	@Override
	public String toString() {
		return "Manager [username=" + username + ", password=" + password + ", managerType=" + managerType + "]";
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

	public ManagerType getManagerType() {
		return managerType;
	}

	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}

}
