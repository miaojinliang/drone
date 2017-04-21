package com.miao.test.common;

import java.util.ArrayList;
import java.util.List;

import com.miao.test.bean.Drone;
import com.miao.test.driver.DroneDriver;
import com.miao.test.driver.RelayDriver;

public interface CommonCore {
	static List<DroneDriver> motorDrivers = new ArrayList<DroneDriver>();//用于装载驱动对象
	static List<RelayDriver> relayDrivers = new ArrayList<RelayDriver>();
	static List<Drone> drones  = new ArrayList<Drone>();//用于装载数据对象
			
}
