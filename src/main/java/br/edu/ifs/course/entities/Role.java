package br.edu.ifs.course.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.edu.ifs.course.entities.enums.RoleName;

@Entity
@Table(name = "tab_roles")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public Role() {
	}

	public Role(Long id, RoleName roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return roleName.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
