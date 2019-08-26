package tech.zxuuu.entity;

public class Product {

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
	@Override
	public String toString() {
		return "Product [product=" + product + ", type=" + type + ", price=" + price + ", picture=" + picture
				+ ", number=" + number + "]";
	} // 学院代码
}

