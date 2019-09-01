package tech.zxuuu.entity;

public class Product {

	private String p_name; // 
	private String type; // 
	private float price; // 
	private String picture; //
	private int number;
	private String information;
	private String buyer;
	private String product;
	
	
	public Product() {}
	
	public String getP_name() {
		return p_name;
	}
	
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

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

}

