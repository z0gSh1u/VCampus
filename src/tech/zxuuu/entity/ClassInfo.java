package tech.zxuuu.entity;

public class ClassInfo {
	private String id;
	private String className;  
	private String time;
    private String teacher;  
    private String classroom;  
    
    public ClassInfo() {}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
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
		this.id=id;
	}
}
