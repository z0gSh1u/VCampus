package tech.zxuuu.entity;

/**
 * 课程信息类（教务系统用）
 */
public class ClassInfo {
	private String id;
	private String className;
	private String time;
	private String teacher;
	private String classroom;
	private String teacherCard;

	public ClassInfo() {
	}

	// 历史遗留问题
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public ClassInfo(String id) {
		this.id = id;
	}

	public String getTeacherCard() {
		return teacherCard;
	}

	public void setTeacherCard(String teacherCard) {
		this.teacherCard = teacherCard;
	}

	public ClassInfo(String id, String className, String time, String teacher, String classroom, String teacherCard) {
		super();
		this.id = id;
		this.className = className;
		this.time = time;
		this.teacher = teacher;
		this.classroom = classroom;
		this.teacherCard = teacherCard;
	}

	@Override
	public String toString() {
		return "ClassInfo [id=" + id + ", className=" + className + ", time=" + time + ", teacher=" + teacher
				+ ", classroom=" + classroom + ", teacherCard=" + teacherCard + "]";
	}

}
