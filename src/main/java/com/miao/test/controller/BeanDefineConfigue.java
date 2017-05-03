package com.miao.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.miao.test.bean.Access;
import com.miao.test.bean.Drone;
import com.miao.test.common.CommonCore;
import com.miao.test.driver.DroneDriver;
import com.miao.test.driver.RelayDriver;
import com.miao.test.service.AccessService;
import com.miao.test.service.DroneService;


public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent>{
//ContextRefreshedEvent为初始化完毕事件，spring还有很多事件可以利用
	
	@Value("#{propertyConfigurer['controll.motorsDrivers']}")
	private String motorDrivers;
	
	
	@Autowired
	private DroneService droneService;
	
	@Autowired
	private AccessService accessService;
	
	
	//当一个ApplicationContext被初始化或刷新触发
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//root application context 没有parent，他就是老大，这里避免执行多次，只执行一次初始化就够了。
		if(event.getApplicationContext().getParent()==null){
			
			List<Drone> drones = droneService.getDrones();
			for(Drone drone : drones){
				//初始化点击驱动
				initDroneDrivers(drone);
				CommonCore.drones.add(drone);
			}
			
//			System.out.println("spring初始化是执行一次");
//			
//			System.out.println(motorDrivers);
//			List<MotorBean> motorBeans = JSONObject.parseArray(motorDrivers, MotorBean.class);
//			//初始化本系统中的马达驱动器
//			initMotorDrivers(motorBeans);
//			//初始化继电器马达
//			initRelayDriver();
		}
	}
	
	
	public void initDroneDrivers(Drone drone){
		List<Access> accesses = accessService.getAccessByDronId(drone.getId());
		DroneDriver dd = new DroneDriver(drone.getId(), drone.getType(),
				drone.getStartDelay(), drone.getDirPin(), drone.getPulPin(),
				drone.getInterval(), drone.getBeforePin(), drone.getBackPin(), drone.getRotateDelay(), accesses);
		CommonCore.motorDrivers.add(dd);
//		for(MotorBean mb : motorBeans){
//			Integer motorNum = mb.getMotorNum();
//			Integer motorDirPin = mb.getDirPin();
//			Integer motorPulPin = mb.getPulPin();
//			Integer motorBackPin = mb.getBackPin();
//			Integer motorStopPin = mb.getStopPin();
//			Long delayTime = mb.getDelayTime();
//			
////			MotorDriver md = new MotorDriver(motorDirPin, motorPulPin, motorNum,motorBackPin,motorStopPin,delayTime);
//			DroneDriver md = new DroneDriver();
//			CommonCore.motorDrivers.add(md);
//		}
	}
	
	public void initRelayDriver(){
		RelayDriver rd = new RelayDriver(12, 13, 14, 15);
		CommonCore.relayDrivers.add(rd);
		
	}
	
	
	
	
}
