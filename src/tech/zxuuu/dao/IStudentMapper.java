package tech.zxuuu.dao;

import java.util.HashMap;
import java.util.List;

import tech.zxuuu.entity.OpenCourseInfo;
import tech.zxuuu.entity.Student;

public interface IStudentMapper {

	public Boolean verifyStudent(Student student);
	
	public String getNameByCardNumber(String cardNumber);
	
	public String getPasswordByUsername(String cardNumber);
	
	public List<OpenCourseInfo> getOpenCourseList();
	
}
