package fit.iuh.edu.entity;

public class ProductType {
	private String idType;
	private String nameType;
	private long price;
	private long lateCharge;
	
	
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getLateCharge() {
		return lateCharge;
	}
	public void setLateCharge(long lateCharge) {
		this.lateCharge = lateCharge;
	}
	
	public ProductType(String idType) {
		super();
		this.idType = idType;
	}
	
	public ProductType(String idType, String nameType, long price, long lateCharge) {
		super();
		this.idType = idType;
		this.nameType = nameType;
		this.price = price;
		this.lateCharge = lateCharge;
	}
	
	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}
}
