package fit.iuh.edu.entity;

public class Customer {
	private String idCustomer;
	private String fullName;
	private String address;
	private String phoneNumber;
	
		
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String idCustomer) {
		super();
		this.idCustomer = idCustomer;
	}
		
	public Customer(String fullName, String address, String phoneNumber) {
		super();
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String idCustomer, String fullName, String address, String phoneNumber) {
		super();
		this.idCustomer = idCustomer;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCustomer == null) ? 0 : idCustomer.hashCode());
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
		Customer other = (Customer) obj;
		if (idCustomer == null) {
			if (other.idCustomer != null)
				return false;
		} else if (!idCustomer.equals(other.idCustomer))
			return false;
		return true;
	}
	
	
}
