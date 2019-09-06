package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.ClassInfo;
import tech.zxuuu.entity.Student;

public interface IClassMapper {

	public List<ClassInfo> getClassInfo(String academy);

	public Boolean takeClass(Student student);

	public String getClassSelection(Student student);

	public ClassInfo getOneClass(String iD);

	public List<ClassInfo> getClassOfOneTeacher(String name);

	public Boolean insertNewCourse(ClassInfo classInfo);

	public Boolean deleteCourse(String id);

}
