package tech.zxuuu.entity;

public class OpenCourseInfo {

	private int id;
	private String courseName;
	private String speaker;
	private String preview;
	private String video;

	public OpenCourseInfo() {
	}

	@Override
	public String toString() {
		return "OpenCourseInfo [id=" + id + ", courseName=" + courseName + ", speaker=" + speaker + ", preview=" + preview
				+ ", video=" + video + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public OpenCourseInfo(int id, String courseName, String speaker, String preview, String video) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.speaker = speaker;
		this.preview = preview;
		this.video = video;
	}

}
