package tech.zxuuu.entity;

public class Book {
	private String title;
	private String author;
	private int numofborrowed;
	private String ISBN;
	private Boolean chargable;
	private String borrower;
	private String category;
	private String details;
	
	public String getBorrower() {
		return borrower;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
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
