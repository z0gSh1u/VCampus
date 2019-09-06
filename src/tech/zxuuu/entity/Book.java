package tech.zxuuu.entity;

/**
 * 书本类（图书馆用）
 */
public class Book {

	private String title;
	private String author;
	private int numofborrowed;
	private String ISBN;
	private int chargable;
	private String borrower;
	private String category;
	private String details;
	private String pictureURL;
	private String borrowTime;
	private String renewornot;

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

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

	public int getChargable() {
		return chargable;
	}

	public void setChargable(int chargable) {
		this.chargable = chargable;
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

	public Book(String title, String author, int numofborrowed, String iSBN, int chargable, String borrower,
			String category, String details, String pictureURL, String borrowTime, String renewornot) {
		super();
		this.title = title;
		this.author = author;
		this.numofborrowed = numofborrowed;
		ISBN = iSBN;
		this.chargable = chargable;
		this.borrower = borrower;
		this.category = category;
		this.details = details;
		this.pictureURL = pictureURL;
		this.borrowTime = borrowTime;
		this.renewornot = renewornot;
	}

	public String getRenewornot() {
		return renewornot;
	}

	public void setRenewornot(String renewornot) {
		this.renewornot = renewornot;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", numofborrowed=" + numofborrowed + ", ISBN=" + ISBN
				+ ", chargable=" + chargable + ", borrower=" + borrower + ", category=" + category + ", details=" + details
				+ ", pictureURL=" + pictureURL + ", borrowTime=" + borrowTime + ", renewornot=" + renewornot + "]";
	}

	public Book() {
	}

}
