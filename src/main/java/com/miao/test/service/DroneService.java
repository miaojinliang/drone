package com.miao.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.Drone;

public interface DroneService {
	List<Drone> getDrones();
	void insertDrone(Drone drone);
	void deleteDrone(Integer id);
	void updateDrone(Drone drone);
	Drone getById(Integer id);
}
