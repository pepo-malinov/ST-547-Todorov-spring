package uni.fmi.masters.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class RoleBean implements Serializable
{
	
	@Id
	@Column(name="role_id")
	@GeneratedValue
	private Integer id;
	
	
	@Column(name="code",unique=true,nullable=false)
	private String code;
	
	
	@Column(name="description",nullable=true)
	private String description;
	
	public RoleBean() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
