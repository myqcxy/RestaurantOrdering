package model;

public class Meal {
	int mid;
	String mname;
	float price;
	String photo;
	String category;
	int sales;
	int addToCacheNumber;
	
	public int getAddToCacheNumber() {
		return addToCacheNumber;
	}
	public void setAddToCacheNumber(int addToCacheNumber) {
		this.addToCacheNumber = addToCacheNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
