package com.miao.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSONObject;
import com.miao.test.common.CommonCore;
import com.miao.test.driver.MotorBean;
import com.miao.test.driver.MotorDriver;
import com.miao.test.driver.RelayDriver;


public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent>{
//ContextRefreshedEvent为初始化完毕事件，spring还有很多事件可以利用
	
	@Value("#{propertyConfigurer['controll.motorsDrivers']}")
	private String motorDrivers;
	
	
	//当一个ApplicationContext被初始化或刷新触发
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//root application context 没有parent，他就是老大，这里避免执行多次，只执行一次初始化就够了。
		if(event.getApplicationContext().getParent()==null){
			System.out.println("spring初始化是执行一次");
			
			System.out.println(motorDrivers);
//			List<MotorBean> motorBeans = JSONObject.parseArray(motorDrivers, MotorBean.class);
//			//初始化本系统中的马达驱动器
//			initMotorDrivers(motorBeans);
//			//初始化继电器马达
//			initRelayDriver();
		}
	}
	
	
	public void initMotorDrivers(List<MotorBean> motorBeans){
		for(MotorBean mb : motorBeans){
			Integer motorNum = mb.getMotorNum();
			Integer motorDirPin = mb.getDirPin();
			Integer motorPulPin = mb.getPulPin();
			Integer motorBackPin = mb.getBackPin();
			Integer motorStopPin = mb.getStopPin();
			Long delayTime = mb.getDelayTime();
			
			MotorDriver md = new MotorDriver(motorDirPin, motorPulPin, motorNum,motorBackPin,motorStopPin,delayTime);
			CommonCore.motorDrivers.add(md);
		}
	}
	
	public void initRelayDriver(){
		RelayDriver rd = new RelayDriver(12, 13, 14, 15);
		CommonCore.relayDrivers.add(rd);
		
	}
	
	
	
	
}
