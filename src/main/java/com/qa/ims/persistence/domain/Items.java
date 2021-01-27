package com.qa.ims.persistence.domain;

public class Items {

	private Long iid;
	private String item_name;
	private Double price;

	public Items(String item_name, Double price) {
		this.setItemName(item_name);
		this.setPrice(price);
	}

	public Items(Long iid, String item_name, Double price) {
		this.setIId(iid);
		this.setItemName(item_name);
		this.setPrice(price);
	}

	public Long getIId() {
		return iid;
	}

	public void setIId(Long iid) {
		this.iid = iid;
	}

	public String getItemName() {
		return item_name;
	}

	public void setItemName(String item_name) {
		this.item_name = item_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item id:" + iid + "\n Item name:" + item_name + "\n price of the item:" + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		result = prime * result + ((iid == null) ? 0 : iid.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (getItemName() == null) {
			if (other.getItemName() != null)
				return false;
		} else if (!getItemName().equals(other.getItemName()))
			return false;
		if (iid == null) {
			if (other.iid != null)
				return false;
		} else if (!iid.equals(other.iid))
			return false;
		if (price == null) { 
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}