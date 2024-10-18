package model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User  implements java.io.Serializable {

     private int id;
     private String fiscalCode;
     private String name;
     private String surname;
     private String sex;
     private String phone;
     private Date dateBirth;
     private String cityBirth;
     
  	@JsonCreator
	public User(@JsonProperty("id") int id, @JsonProperty("fiscalCode") String fiscalCode, 
			@JsonProperty("name") String name, @JsonProperty("surname") String surname, 
			@JsonProperty("sex") String sex, @JsonProperty("phone") String phone, 
			@JsonProperty("dateBirth")Date dateBirth, @JsonProperty("cityBirth") String cityBirth) {
		super();
		this.id = id;
		this.fiscalCode = fiscalCode;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.phone = phone;
		this.dateBirth = dateBirth;
		this.cityBirth = cityBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getCityBirth() {
		return cityBirth;
	}

	public void setCityBirth(String cityBirth) {
		this.cityBirth = cityBirth;
	}
}