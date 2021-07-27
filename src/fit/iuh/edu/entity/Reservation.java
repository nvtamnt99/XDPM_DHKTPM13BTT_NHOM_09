package fit.iuh.edu.entity;

import fit.iuh.edu.entity.Customer;
import fit.iuh.edu.entity.Product;

public class Reservation {
	private String idReservation;
	private long amount;
	private boolean status;
	private Customer customer;
	private Product product;
	
	public String getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(String idReservation) {
		this.idReservation = idReservation;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(String idReservation, long amount, boolean status, Customer customer, Product product) {
		super();
		this.idReservation = idReservation;
		this.amount = amount;
		this.status = status;
		this.customer = customer;
		this.product = product;
	}
	public Reservation(long amount, boolean status, Customer customer, Product product) {
		super();
		this.amount = amount;
		this.status = status;
		this.customer = customer;
		this.product = product;
	}
}
