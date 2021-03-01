package com.biatest.microservice.PowerMeter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Device {
// The device object with all the information that we need
	 
	private String device_sn;
	private HashMap<Timestamp, Double> energyPerTimestamp= new HashMap<>(); // I am using a Hashmap here
	//to keep all values that will be send. If this gets too big I can dump to a db, based on the text on deviceDao.
	//I believe it's important to keep the raw data in a nice controlled form. Using the device as primary key for my
	//object and basically the timestamp as a secondary key
	
	
	private Timestamp timestamp;
	private double powerValue;
	private double energy;

	protected Device() {
	}

	public Device( Timestamp timestamp,String device_sn, double energy) {
		super();
		this.device_sn = device_sn;
		this.timestamp = timestamp;
		this.energy = energy;
	}

	public String getDevice_sn() {
		return device_sn;
	}

	public void setDevice_sn(String device_sn) {
		this.device_sn = device_sn;
	}

	public HashMap<Timestamp, Double> getEnergyPerTimestamp() {
		return energyPerTimestamp;
	}

	public void setEnergyPerTimestamp(Timestamp time, double energy) {
		this.energyPerTimestamp.put(time, energy);
	}

	public double getPowerValue() {
		return powerValue;
	}

	public void setPowerValue(double powerValue) {
		this.powerValue = powerValue;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
