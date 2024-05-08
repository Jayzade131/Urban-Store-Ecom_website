package com.ecom.entity;
import com.ecom.constants.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String password;
	private String email;
	private String name;
	private UserRole role;
	
	@Lob
	@Column(columnDefinition ="longblob")
	private byte[] profile;
	
}
