package tech.zxuuu.dao;

import tech.zxuuu.entity.Teacher;

public interface ITeacherMapper {

	public Boolean verifyTeacher(Teacher teacher);
	
	public Teacher getTeacherDetailByCardNumber(String cardNumber);
	
}
