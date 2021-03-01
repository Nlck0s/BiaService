package com.biatest.microservice.PowerMeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class DeviceDao { // This is basically the handler of all the device objects

	
	private static HashMap<String, Device> devices = new HashMap<>();
	
	/*Saving only to ram here and not on a db for 2 reasons:
	1) To save time and not make it too complex
	2) To make it faster. No matter which db is used memory is always faster. So we can see how active
	are the devices and keep the ones that send messages without stop in ram, while the ones that are less active
	for various reason can be stored to a db and be picked up when needed.
	All devices should be backed up in a database in frequent time periods
	*/

	public Device save(Device device) {
        // A simple debug logger entry
		if (!devices.containsKey(device.getDevice_sn())) { //if device does not exist create
			double powerValue = device.getEnergy();
			device.setPowerValue(powerValue);
			device.setEnergyPerTimestamp(device.getTimestamp(), device.getEnergy());
			devices.put(device.getDevice_sn(), device);
		} else if (devices.containsKey(device.getDevice_sn())) { // if it does update
			Device tempDevice = devices.get(device.getDevice_sn());
			tempDevice.setEnergyPerTimestamp(device.getTimestamp(), device.getEnergy());
			double powerValue =device.getEnergy() + devices.get(device.getDevice_sn()).getPowerValue(); // very simplified logic to calculate power
			tempDevice.setPowerValue(powerValue / tempDevice.getEnergyPerTimestamp().size());//previous power+ new energy / total of readings
			devices.put(device.getDevice_sn(), tempDevice);								 // did not spend more effort in that because it'
																						// could have used the timestamps as well
			// A simple debug logger case
			return tempDevice;																	
		}
		// A simple debug logger exit
		return device;
	}
	
	public HashMap<String, Device> findAll() {
		return devices;
	}
	
	

}
