package tech.zxuuu.net;

import tech.zxuuu.entity.*;

/**
 * 在请求中携带的用户信息
 * 
 * @author z0gSh1u
 */

public class Session {

	private Student student = null;
	private Teacher teacher = null;
	private Manager manager = null;
	private UserType userType = null;

	public Student getStudent() {
		return student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Manager getManager() {
		return manager;
	}

	public UserType getUserType() {
		return userType;
	}

	public Session() {}
	
	public Session(Student student) {
		this.student = student;
		this.userType = UserType.STUDENT;
	}

	public Session(Teacher teacher) {
		this.teacher = teacher;
		this.userType = UserType.TEACHER;
	}

	public Session(Manager manager) {
		this.manager = manager;
		this.userType = UserType.MANAGER;
	}

	@Override
	public String toString() {
		return "Session [student=" + student + ", teacher=" + teacher + ", manager=" + manager + ", userType=" + userType
				+ "]";
	}

}
