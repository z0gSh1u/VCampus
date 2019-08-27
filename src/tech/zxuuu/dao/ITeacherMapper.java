package tech.zxuuu.dao;

import tech.zxuuu.entity.Teacher;

public interface ITeacherMapper {

	public Teacher verifyTeacher(String cardNumber, String password);
	
}
