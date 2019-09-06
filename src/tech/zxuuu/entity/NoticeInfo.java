package tech.zxuuu.entity;

/**
 * 新闻通知类（学生主页用）
 */
public class NoticeInfo {

	private String title;
	private String date;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public NoticeInfo() {
	}

	@Override
	public String toString() {
		return "NoticeInfo [title=" + title + ", date=" + date + ", url=" + url + "]";
	}

	public NoticeInfo(String title, String date, String url) {
		super();
		this.title = title;
		this.date = date;
		this.url = url;
	}

}
