package com.tutorinfo.dto;

import javax.validation.constraints.NotEmpty;


public class StudentDto {

	private Long id; 
	@NotEmpty
	private String enrollmentNo;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String address;

	@NotEmpty
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(String enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", enrollmentNo=" + enrollmentNo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", email=" + email + "]";
	}

	
}
