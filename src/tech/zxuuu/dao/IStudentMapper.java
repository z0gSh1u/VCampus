package tech.zxuuu.dao;

import tech.zxuuu.entity.Student;

public interface IStudentMapper {

	/* Auth模块 */
	public Boolean verifyStudent(Student student);
	public Student getStudentDetailByCardNumber(String cardNumber);
	/* Auth模块 */
	
	public String getNameByCardNumber(String cardNumber);
	
	public String getPasswordByUsername(String cardNumber);
	
	public Boolean insertStudent(Student student);
	
	public int deleteStudent(String cardnumber);
	
	public int searchStudentByCardNumber(String cardnumber);
	
	public int searchStudentByStudentNumber(String studentnumber);
	
	public int switchStudent(Student student);
	
}
