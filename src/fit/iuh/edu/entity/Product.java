package fit.iuh.edu.entity;

import fit.iuh.edu.entity.Title;

public class Product {
	private String idProduct;
	private String status;
	private Title title;
	
	public String getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String idProduct) {
		super();
		this.idProduct = idProduct;
	}
	
	public Product(String idProduct, String status, Title title) {
		super();
		this.idProduct = idProduct;
		this.status = status;
		this.title = title;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
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
		Product other = (Product) obj;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		return true;
	}
	
}
