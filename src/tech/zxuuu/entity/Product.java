package tech.zxuuu.entity;

public class Product {

<<<<<<< HEAD
	private String p_name; // 
	private String type; // 
	private float price; // 
	private String picture; //
	private int number;
	private String information;
	private String buyer;
	
	
	public Product() {}
	
	
	public String getP_name() {
		return p_name;
	}
	
	public void setP_name(String p_name) {
		this.p_name = p_name;
=======
	private String product; // 一卡通号
	private String type; // 学号
	private float price; // 密码（MD5）
	private String picture; // 姓名
	private int number;
	public String getProduct() {
		return product;
	}
	
	public Product() {}
	public void setProduct(String product) {
		this.product = product;
>>>>>>> 34e7912ff789559df32cb4e6a2ca946e890d0d4b
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
<<<<<<< HEAD
	
	
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	
	public String getBuyer() {
		return buyer;
	}


	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	@Override
	public String toString() {
		return "Product [p_name=" + p_name + ", type=" + type + ", price=" + price + ", picture=" + picture
				+ ", number=" + number + ", information=" + information + ", buyer=" + buyer + "]";
	} // 
=======
	@Override
	public String toString() {
		return "Product [product=" + product + ", type=" + type + ", price=" + price + ", picture=" + picture
				+ ", number=" + number + "]";
	} // 学院代码
>>>>>>> 34e7912ff789559df32cb4e6a2ca946e890d0d4b
}

