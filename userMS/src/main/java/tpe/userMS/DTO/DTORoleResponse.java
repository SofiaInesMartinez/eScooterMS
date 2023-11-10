package tpe.userMS.DTO;

import tpe.userMS.model.Role;

public class DTORoleResponse {
	private long id;
	private String name;
	
	public DTORoleResponse() {
		super();
	}
	
	public DTORoleResponse(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public DTORoleResponse(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
