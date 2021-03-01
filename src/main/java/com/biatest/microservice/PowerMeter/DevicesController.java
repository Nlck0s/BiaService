package com.biatest.microservice.PowerMeter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DevicesController {

	@Autowired
	private DeviceDao service;
	/*
	 * A simple controller to receive the post and get. Tried to K I S S as this is
	 * more of a concept that an actual service. I would certainly add more logging,
	 * exception handling and security for the calls
	 */

	@GetMapping("/list")
	public ResponseEntity<String> getDevicesInfo() {
		// A simple debug logger entry
		String all = "";
		HashMap<String, Device> devices = service.findAll();
		Iterator it = devices.entrySet().iterator(); // very messy but just for basic visualization
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Device tempDevice = (Device) pair.getValue();
			all += "Device with Serial number: " + pair.getKey() + " has Power equal to " + tempDevice.getPowerValue()
					+ " and Datapoints: " + System.lineSeparator();
			Iterator itDevice = tempDevice.getEnergyPerTimestamp().entrySet().iterator();
			while (itDevice.hasNext()) {
				Map.Entry pair1 = (Map.Entry) itDevice.next();
				all += "TimeStamp: " + pair1.getKey() + " Energy: " + pair1.getValue() + System.lineSeparator();
			}
		}
		// A simple debug logger exit
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateDeviceInfo(@RequestBody Device device) {
		// A simple debug logger entry
		Device savedDevice = service.save(device);
		// A simple debug logger exit
		return new ResponseEntity<>("Device " + savedDevice.getDevice_sn() + " Updated/Created", HttpStatus.OK);
	}

}
