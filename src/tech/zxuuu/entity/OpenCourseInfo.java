package tech.zxuuu.entity;

public class OpenCourseInfo {
	private int id;
	private String courseName;
	private String speaker;
	private String preview;
	
	public OpenCourseInfo() {}
	
	public OpenCourseInfo(int id, String courseName, String speaker, String preview){
		this.id = id;
		this.courseName = courseName;
		this.speaker = speaker;
		this.preview = preview;
	}

	public int getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getSpeaker() {
		return speaker;
	}

	public String getPreview() {
		return preview;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}
}
