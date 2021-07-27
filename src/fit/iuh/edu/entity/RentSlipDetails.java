package fit.iuh.edu.entity;

import java.util.Date;

import fit.iuh.edu.entity.Product;
import fit.iuh.edu.entity.RentSlip;

public class RentSlipDetails {
	private long price;
	private Date returnDate;
	private RentSlip rentSlip;
	private Product product;
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public RentSlip getRentSlip() {
		return rentSlip;
	}
	public void setRentSlip(RentSlip rentSlip) {
		this.rentSlip = rentSlip;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public RentSlipDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentSlipDetails(long price, Date returnDate, RentSlip rentSlip, Product product) {
		super();
		this.price = price;
		this.returnDate = returnDate;
		this.rentSlip = rentSlip;
		this.product = product;
	}
	public RentSlipDetails(long price, Date returnDate, Product product) {
		super();
		this.price = price;
		this.returnDate = returnDate;
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		RentSlipDetails other = (RentSlipDetails) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
