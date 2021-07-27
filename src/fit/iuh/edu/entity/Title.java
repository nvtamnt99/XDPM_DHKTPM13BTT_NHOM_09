package fit.iuh.edu.entity;

import java.util.List;

import fit.iuh.edu.entity.Product;
import fit.iuh.edu.entity.ProductType;

public class Title {
	private String idTitle;
	private String nameTitle;
	private String type;
	private ProductType pType;
	private List<Product> list;
	
	public String getIdTitle() {
		return idTitle;
	}
	public void setIdTitle(String idTitle) {
		this.idTitle = idTitle;
	}
	public String getNameTitle() {
		return nameTitle;
	}
	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ProductType getpType() {
		return pType;
	}
	public void setpType(ProductType pType) {
		this.pType = pType;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public Title() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Title(String idTitle, String nameTitle, String type, ProductType pType, List<Product> list) {
		super();
		this.idTitle = idTitle;
		this.nameTitle = nameTitle;
		this.type = type;
		this.pType = pType;
		this.list = list;
	}
	public Title(String idTitle, String nameTitle, String type, ProductType pType) {
		super();
		this.idTitle = idTitle;
		this.nameTitle = nameTitle;
		this.type = type;
		this.pType = pType;
	}
	public Title(String idTitle) {
		super();
		this.idTitle = idTitle;
	}
	public Title(String nameTitle, String type, ProductType pType) {
		super();
		this.nameTitle = nameTitle;
		this.type = type;
		this.pType = pType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTitle == null) ? 0 : idTitle.hashCode());
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
		Title other = (Title) obj;
		if (idTitle == null) {
			if (other.idTitle != null)
				return false;
		} else if (!idTitle.equals(other.idTitle))
			return false;
		return true;
	}
}
