package es.uji.ei1027.skillsharing.model;

import java.util.Date;

public class Student {

	private String nid; 
	private String name; 
	private String username; 
	private String password; 
	private String mail; 
	private int course; 
	private Date offerHours;
	private Date demandHours;
	private boolean banned;

	public String getNid() {
		
		return nid;
		
	}

	public void setNid(String nid) {
		
		this.nid = nid.toUpperCase(); //La letra del DNI se convertira a mayusculas para coincidir con la BD.
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public String getUsername() {
		
		return username;
		
	}

	public void setUsername(String username) {
		
		this.username = username;
		
	}

	public String getPassword() {
		
		return password;
		
	}

	public void setPassword(String password) {
		
		this.password = password;
		
	}

	public String getMail() {
		
		return mail;
		
	}

	public void setMail(String mail) {
		
		this.mail = mail;
		
	}

	public int getCourse() {
		
		return course;
		
	}

	public void setCourse(int course) {
		
		this.course = course;
		
	}

	public Date getOfferHours() {
		
		return offerHours;
		
	}

	public void setOfferHours(Date offerHours) {
		
		this.offerHours = offerHours;
		
	}

	public Date getDemandHours() {
		
		return demandHours;
		
	}

	public void setDemandHours(Date demandHours) {
		
		this.demandHours = demandHours;
		
	}

	public boolean getBanned() {
		
		return banned;
		
	}

	public void setBanned(boolean banned) {
		
		this.banned = banned;
		
	}
	
	@Override
	public String toString() {
		
		return "Demand [nid = " + nid + ", name = " + name +", username = " + username + ", password = " + password + ", mail = " + mail + ", course = " + course+ ", offerHours = " + offerHours + ", demandHours = " + demandHours + ", banned = " + banned + "]";
	
	}
	
}