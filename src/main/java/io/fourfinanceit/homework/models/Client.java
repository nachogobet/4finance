package io.fourfinanceit.homework.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	private Long id;
	private String name;
	private String surname;
	private List<Loan> loans;
	
	public Client(){}
	
	public Client(String name, String surname) {
		this.loans = new ArrayList<Loan>();
		this.name = name;
		this.surname = surname;
	}
	@Id
    @Column(name="id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="surname", nullable=false)
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	public List<Loan> getLoans() {
		return loans;
	}
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	
}
