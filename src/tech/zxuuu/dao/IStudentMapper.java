package tech.zxuuu.dao;

import tech.zxuuu.entity.Student;

public interface IStudentMapper {

	public Boolean verifyStudent(Student student);
	
	public String getNameByCardNumber(String cardNumber);
	
	public String getPasswordByUsername(String cardNumber);
	
}
