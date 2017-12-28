package com.packt.webstore.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

public class Customer {

	private Integer id;
	@SafeHtml
	@NotBlank(message="{Customer.incorrectEmptyCustomerName}")
	private String name;
	@SafeHtml
	@NotBlank(message="{Customer.incorrectEmptyCustomerSurnameName}")
	private String surname;
	@SafeHtml
	@NotBlank(message="{Customer.incorrectEmptyCustomerCity}")
	private String city;
	@SafeHtml
	@NotBlank(message="{Customer.incorrectEmptyCustomerStreet}")
	private String street;
	@SafeHtml
	@NotBlank(message="{Customer.incorrectEmptyCustomerStreetNumber}")
	private String streetNumber;
	@SafeHtml
	@NotBlank(message="{Customer.incorrectEmptyCustomerPhoneNumber}")
	private String phoneNumber;
	@SafeHtml
	@Email(message="{Customer.incorrectCustomerEmail}")
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
