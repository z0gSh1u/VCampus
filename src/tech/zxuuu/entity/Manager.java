package tech.zxuuu.entity;

/**
 * 各类管理员实体类
 * @author z0gSh1u
 */

public class Manager {

	private String cardNumber; // 用户名
	private String password; // 密码（MD5）
	/**
	 * @see ManagerType
	 */
	private ManagerType managerType; // 管理员类型
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Manager(String cardNumber, String password, ManagerType managerType) {
		super();
		this.cardNumber = cardNumber;
		this.password = password;
		this.managerType = managerType;
	}

	@Override
	public String toString() {
		return "Manager [cardNumber=" + cardNumber + ", password=" + password + ", managerType=" + managerType + "]";
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
