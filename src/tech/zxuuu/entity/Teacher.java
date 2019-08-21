package tech.zxuuu.entity;

/**
 * 教师类
 * @author z0gSh1u
 */

public class Teacher {

	private String username; // 用户名
	private String password; // 密码（MD5）
	private String name; // 姓名
	private String academy; // 学院代码

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public Teacher(String username, String password, String name, String academy) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.academy = academy;
	}

	@Override
	public String toString() {
		return "Teacher [username=" + username + ", password=" + password + ", name=" + name + ", academy=" + academy + "]";
	}

}
