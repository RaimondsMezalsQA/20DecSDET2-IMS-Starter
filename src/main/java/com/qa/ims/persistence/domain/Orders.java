package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private Long oid;
	private Customer oCustomer;
	private Double value;
	List<Items> oItems = new ArrayList<Items>();

	public Orders() {
		
	}
	
	public Orders(Customer oCustomer) {
		this.setOCustomer(oCustomer);
	}
	
	public Orders(Customer oCustomer, Double value, List<Items> oItems) {
		this.setOCustomer(oCustomer);
		this.setValue(value);
		this.setOItems(oItems);
	}

	public Orders(Long oid, Customer oCustomer, Double value, List<Items> oItems) {
		this.setOid(oid);
		this.setOCustomer(oCustomer);
		this.setValue(value);
		this.setOItems(oItems);
	}

	public Orders(Long oid, Customer oCustomer) {
		// TODO Auto-generated constructor stub
		this.setOid(oid);
		this.setOCustomer(oCustomer);
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Customer getOCustomer() {
		return oCustomer;
	}

	public void setOCustomer(Customer oCustomer) {
		this.oCustomer = oCustomer;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public List<Items> getOItems() {
		return oItems;
	}

	public void setOItems(List<Items> items) {
		this.oItems = items;
	}

	@Override
	public String toString() {
		StringBuilder order = new StringBuilder();
		order.append(String.format(": " + this.oid + " " + oCustomer.getFirstName() + " " + oCustomer.getSurname()));
		if (this.oItems.isEmpty()) {
			order.append("\n -> There are no items within this order!");
		}else {
			order.append(" - value of the order is = £" + this.value);
			this.oItems.forEach(item -> {
				order.append(item.getItemName() + ": £" + item.getPrice());
			});
		}
		return order.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oCustomer == null) ? 0 : oCustomer.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((oItems == null) ? 0 : oItems.hashCode());
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
		Orders other = (Orders) obj;
		if (getOCustomer() == null) {
			if (other.getOCustomer() != null)
				return false;
		} else if (!getOCustomer().equals(other.getOCustomer()))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (getOItems() == null) {
			if (other.getOItems() != null)
				return false;
		} else if (!getOItems().equals(other.getOItems()))
			return false;
		return true;
	}
	
	public Double totalValue() {
		
		this.oItems.forEach(item -> {
			value += item.getPrice();
		});
		
		return value;
	}

}