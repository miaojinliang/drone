package com.miao.test.common;

import java.util.ArrayList;
import java.util.List;

import com.miao.test.driver.MotorDriver;
import com.miao.test.driver.RelayDriver;

public interface CommonCore {
	static List<MotorDriver> motorDrivers = new ArrayList<MotorDriver>();
	static List<RelayDriver> relayDrivers = new ArrayList<RelayDriver>();
}
