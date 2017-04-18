package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.Drone;

public interface DroneMapper {
	List<Drone> getDrones();
	void insertDrone(Drone drone);
	void deleteDrone(@Param("id") Integer id);
	void updateDrone(Drone drone);
	
}
