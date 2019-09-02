package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.EmoticonInfo;
import tech.zxuuu.entity.OpenCourseInfo;

public interface IOpenCourseMapper {

	public List<OpenCourseInfo> getOpenCourseList();
	
	public List<EmoticonInfo> getEmoticonList(); 

	public String getEmoticon(String emoName);

	public Boolean insertNewOpencourse(OpenCourseInfo openCourseInfo);

	public Integer getMaxId();

	public Boolean deleteOpencourse(Integer id);

}
