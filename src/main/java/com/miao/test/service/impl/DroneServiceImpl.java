package com.miao.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miao.test.bean.Drone;
import com.miao.test.mapper.DroneMapper;
import com.miao.test.service.DroneService;

@Service
public class DroneServiceImpl implements DroneService {
	
	@Autowired
	private DroneMapper droneMapper;

	@Override
	public List<Drone> getDrones() {
		return droneMapper.getDrones();
	}

	@Override
	public void insertDrone(Drone drone) {
		droneMapper.insertDrone(drone);
	}

	@Override
	public void deleteDrone(Integer id) {
		droneMapper.deleteDrone(id);
	}

	@Override
	public void updateDrone(Drone drone) {
		droneMapper.updateDrone(drone);
	}

	@Override
	public Drone getById(Integer id) {
		return droneMapper.getById(id);
	}

	@Override
	public void updateInterval(Integer id, Integer interval) {
		droneMapper.updateInterval(id, interval);
	}
}
