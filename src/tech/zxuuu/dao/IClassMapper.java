package tech.zxuuu.dao;

import java.util.List;

import javax.activation.MailcapCommandMap;

import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;

public interface IClassMapper {
	public List<ClassInfo> getClassInfo(Student a);

	public Boolean takeClass(Student student);

	public String getClassSelection(Student student);

	public ClassInfo getOneClass(String iD);

	public List<ClassInfo> getClassOfOneTeacher(String name);
}
