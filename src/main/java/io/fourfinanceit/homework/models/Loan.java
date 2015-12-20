package io.fourfinanceit.homework.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

@Entity 
@Table(name = "loan")
public class Loan {
	private Long id;
	private BigDecimal amount;
	private Integer term;
	private Client client;
	private String status;
	private Date requested;
	private String ip;
	
	public Loan(){}
	
	public Loan(BigDecimal amount, Integer term, Client client, String status, Date requested, String ip){
		this.amount = amount;
		this.term = term;
		this.client = client;
		this.status = status;
		this.requested = requested;
		this.ip = ip;
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
	
	@Column(name="amount", nullable=false)
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name="term", nullable=false)
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "requested", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRequested() {
		return requested;
	}
	public void setRequested(Date requested) {
		this.requested = requested;
	}
	
	@Column(name="ip")
	@Length(max = 15)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}	
		
}
