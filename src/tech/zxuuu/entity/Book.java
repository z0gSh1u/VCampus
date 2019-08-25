package tech.zxuuu.entity;

public class Book {
	private String title;
	private String author;
	private int numofborrowed;
	private String ISBN;
	private Boolean chargable;
	
	public Boolean getChargable() {
		return chargable;
	}
	public void setChargable(Boolean chargable) {
		this.chargable = chargable;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", numofborrowed=" + numofborrowed + ", ISBN=" + ISBN
				+ "]";
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNumofborrowed() {
		return numofborrowed;
	}
	public void setNumofborrowed(int numofborrowed) {
		this.numofborrowed = numofborrowed;
	}
	
	public Book(String title, String author, int numofborrowed, Boolean chargable) {
		super();
		this.title = title;
		this.author = author;
		this.numofborrowed = numofborrowed;
		this.chargable = chargable;
	}
	public Book()
	{}
	

}
