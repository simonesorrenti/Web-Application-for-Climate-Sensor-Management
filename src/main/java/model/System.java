package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class System  implements java.io.Serializable {

     private Integer id;
     private Integer owner;
     private String nameSystem;
     private String city;
     private String address;
     private String houseNumber;
     private String description;
     private int cap;
     
 	@JsonCreator
	public System(@JsonProperty("id") Integer id, @JsonProperty("owner") Integer owner, 
			@JsonProperty("nameSystem") String nameSystem, @JsonProperty("city") String city, 
			@JsonProperty("address") String address, @JsonProperty("houseNumber") String houseNumber,
			@JsonProperty("description") String description,@JsonProperty("cap") int cap) {
		super();
		this.id = id;
		this.owner = owner;
		this.nameSystem = nameSystem;
		this.city = city;
		this.address = address;
		this.houseNumber = houseNumber;
		this.description = description;
		this.cap = cap;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getNameSystem() {
		return nameSystem;
	}

	public void setNameSystem(String nameSystem) {
		this.nameSystem = nameSystem;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}
}