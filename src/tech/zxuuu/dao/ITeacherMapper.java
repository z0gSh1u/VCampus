package tech.zxuuu.dao;

import java.util.Map;

import tech.zxuuu.entity.Teacher;

public interface ITeacherMapper {

	public Boolean verifyTeacher(Teacher teacher);
	
	public Teacher getTeacherDetailByCardNumber(String cardNumber);
	
	public String getTeacherNameById(Map<String, String> mp);
	
}
