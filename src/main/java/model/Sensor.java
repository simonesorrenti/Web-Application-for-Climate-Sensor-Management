package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensor  implements java.io.Serializable {

     private int id;
     private int position;
     private String type;
     private String brand;
     
   	@JsonCreator
	public Sensor(@JsonProperty("id") int id, @JsonProperty("position") int position, 
			@JsonProperty("type") String type, @JsonProperty("brand") String brand) {
		super();
		this.id = id;
		this.position = position;
		this.type = type;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}