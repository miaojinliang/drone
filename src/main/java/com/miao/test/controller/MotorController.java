package com.miao.test.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miao.test.bean.MotorDriver;
import com.miao.test.common.CommonCore;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Controller
public class MotorController {
	
	@RequestMapping(value = "/getMotors")
	@ResponseBody
	public List<MotorDriver> getMotors(){
		return CommonCore.motorDrivers;
	}
	
	@RequestMapping(value = "/motorMove")
	@ResponseBody
	public void motorMove(@RequestParam("moterNum") Integer motorNum,@RequestParam("direction") Integer direction) throws InterruptedException{
		
//		if(cacheManager.getCache("raspTest").get("run_"+motorNum)==null||!Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run_"+motorNum).getObjectValue().toString())){
//			Element element = new Element("run_"+motorNum, true);  
//			cacheManager.getCache("raspTest").put(element);
			for(MotorDriver md : CommonCore.motorDrivers){
				if(md.getMotorNum()==motorNum){
					md.setDirection(direction);
					if(!md.getRunning()){//说明在转动中
//						md.setCacheManager(cacheManager);
						md.setRunning(true);
//						md.moveThread();
						md.move();
					}
				}
			}
//		}else{
//			for(MotorDriver md : CommonCore.motorDrivers){
//				if(md.getMotorNum()==motorNum){
//					md.setDirection(direction);
//				}
//			}
//		}
	}
	
	
	@RequestMapping(value = "/motorMoveFen")
	@ResponseBody
	public void motorMoveFen(@RequestParam("moterNum") Integer motorNum,@RequestParam("direction") Integer direction,@RequestParam("fen") Integer fen) throws InterruptedException{
		
//		if(cacheManager.getCache("raspTest").get("run_"+motorNum)==null||!Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run_"+motorNum).getObjectValue().toString())){
//			Element element = new Element("run_"+motorNum, true);  
//			cacheManager.getCache("raspTest").put(element);
			for(MotorDriver md : CommonCore.motorDrivers){
				if(md.getMotorNum()==motorNum){
					md.setDirection(direction);
					if(!md.getRunning()){//说明在转动中
//						md.setCacheManager(cacheManager);
//						md.setRunning(true);
						md.moveCycle(fen);
					}
				}
			}
	}
	
	@RequestMapping(value = "/setInterval")
	@ResponseBody
	public Integer setInterval(@RequestParam("motorNum") Integer motorNum,@RequestParam("intervalMth") String intervalMth){
		Integer interval=5;
		for(MotorDriver md : CommonCore.motorDrivers){
			if(md.getMotorNum()==motorNum){
				interval = md.getInterval();
				if("UP".equals(intervalMth)){
					interval = interval-1;
				}else{
					interval = interval+1;
				}
				md.setInterval(interval);
				interval = md.getInterval();
//				md.setRunning(false);
			}
		}
		return 10-interval;
	}
	
	@RequestMapping(value = "/motorPuase")
	@ResponseBody
	public void motorPuase(@RequestParam("motorNum") Integer motorNum){
		for(MotorDriver md : CommonCore.motorDrivers){
			if(md.getMotorNum()==motorNum){
				md.setRunning(false);
//				Element element = new Element("run_"+motorNum, true);  
//				cacheManager.getCache("raspTest").put(element);
//				md.stop();
			}
		}
	}
}
