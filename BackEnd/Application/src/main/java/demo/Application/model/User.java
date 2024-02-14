package demo.Application.model;


import java.util.Date;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String fname;
	private String mname;
	private String lname;
	private String email;
	private String password;
	private String type;
	private Long mobile;
	private Date birthdate;
	private String address;
	private String city;
	private String state;
	private String contry;
	private Long adharno;
	private String panno;
	private String cast;
	private Long castno;
	private String domecile;
	private Long domecileno;
	private String nationality;
	private Long nationalityno;
	private String formstatus;
	
}
