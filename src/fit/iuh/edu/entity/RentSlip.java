package fit.iuh.edu.entity;

import java.util.Date;
import java.util.List;

import fit.iuh.edu.entity.RentSlipDetails;

public class RentSlip {
	private String idRental;
	private Date rentalDate;
	private String idCustomer;
	
	private List<RentSlipDetails> listDetails;

	public String getIdRental() {
		return idRental;
	}

	public void setIdRental(String idRental) {
		this.idRental = idRental;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public List<RentSlipDetails> getListDetails() {
		return listDetails;
	}

	public void setListDetails(List<RentSlipDetails> listDetails) {
		this.listDetails = listDetails;
	}

	public RentSlip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentSlip(Date rentalDate) {
		super();
		this.rentalDate = rentalDate;
	}

	public RentSlip(String idRental, Date rentalDate, String idCustomer) {
		super();
		this.idRental = idRental;
		this.rentalDate = rentalDate;
		this.idCustomer = idCustomer;
	}
	
	public RentSlip(Date rentalDate, List<RentSlipDetails> listDetails) {
		super();
		this.rentalDate = rentalDate;
		this.listDetails = listDetails;
	}

	public RentSlip(Date rentalDate, String idCustomer) {
		super();
		this.rentalDate = rentalDate;
		this.idCustomer = idCustomer;
	}

	public long calcTotalAmount() {
		long total = 0;
		for (RentSlipDetails rentSlipDetails : listDetails) {
			total += rentSlipDetails.getPrice();
		}
		return total;
	}
}
