package model;

import java.util.Date;

public class Detection  implements java.io.Serializable {

     private int idDetection;
     private int idSensor;
     private String valueDetection;
     private Date dateDetection;
     private String typeSensor;
     private String brandSensor;
     private String position;
     
	public Detection(int idDetection, int idSensor, String valueDetection, Date dateDetection, String typeSensor,
			String brandSensor, String position) {
		super();
		this.idDetection = idDetection;
		this.idSensor = idSensor;
		this.valueDetection = valueDetection;
		this.dateDetection = dateDetection;
		this.typeSensor = typeSensor;
		this.brandSensor = brandSensor;
		this.position = position;
	}

	public int getIdDetection() {
		return idDetection;
	}

	public void setIdDetection(int idDetection) {
		this.idDetection = idDetection;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public String getValueDetection() {
		return valueDetection;
	}

	public void setValueDetection(String valueDetection) {
		this.valueDetection = valueDetection;
	}

	public Date getDateDetection() {
		return dateDetection;
	}

	public void setDateDetection(Date dateDetection) {
		this.dateDetection = dateDetection;
	}

	public String getTypeSensor() {
		return typeSensor;
	}

	public void setTypeSensor(String typeSensor) {
		this.typeSensor = typeSensor;
	}

	public String getBrandSensor() {
		return brandSensor;
	}

	public void setBrandSensor(String brandSensor) {
		this.brandSensor = brandSensor;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}     
}