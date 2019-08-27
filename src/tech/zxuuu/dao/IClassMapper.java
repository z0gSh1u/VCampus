package tech.zxuuu.dao;

import java.util.List;

import javax.activation.MailcapCommandMap;

import tech.zxuuu.client.teaching.ClassInfo;
import tech.zxuuu.entity.Student;

public interface IClassMapper {
	public List<ClassInfo> getClassInfo(Student a);
}
