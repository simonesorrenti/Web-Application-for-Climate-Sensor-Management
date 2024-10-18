package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Position  implements java.io.Serializable {

     private int id;
     private int system;
     private String namePosition;
     private String description;
     
  	@JsonCreator
	public Position(@JsonProperty("id") int id, @JsonProperty("system") int system, @JsonProperty("namePosition") String namePosition,
			 @JsonProperty("description") String description) {
		super();
		this.id = id;
		this.system = system;
		this.namePosition = namePosition;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSystem() {
		return system;
	}

	public void setSystem(int system) {
		this.system = system;
	}

	public String getNamePosition() {
		return namePosition;
	}

	public void setNamePosition(String namePosition) {
		this.namePosition = namePosition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}