package tpe.userMS.model;

import java.util.Objects;

public class Role {

	private int id;
	private String type;
	
	
	public Role(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", type=" + type + "]";
	}



}
