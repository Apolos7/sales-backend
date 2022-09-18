package br.edu.ifs.course.entities.enums;

public enum RoleName {
	
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	private String name;

	private RoleName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
