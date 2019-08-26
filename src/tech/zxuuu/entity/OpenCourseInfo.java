package tech.zxuuu.entity;

public class OpenCourseInfo {
	private int id;
	private String courseName;
	private String speaker;
	private String preview;
	
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
}
