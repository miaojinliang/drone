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
@RequestMapping("/testController")
public class TestController {
	
	
	/*@Autowired
	private CacheManager cacheManager;
	
	private List<MotorDriver> md=null;
	
	@RequestMapping(value = "/addMotors")
	@ResponseBody
	public void addMotors(@RequestParam("dirPin") Integer dirPin,
			@RequestParam("pulPin") Integer pulPin,
			@RequestParam("motorNum") Integer motorNum){
//		MotorDriver md = new MotorDriver(dirPin, pulPin, motorNum);
//		CommonCore.motorDrivers.add(md);
	}
	@RequestMapping(value = "/listMotors")
	@ResponseBody
	public void listMotors(){
		
		for(MotorDriver md : CommonCore.motorDrivers){
			System.out.println("----马达编号："+md.getMotorNum());
		}
	}
	
	@RequestMapping(value = "/getMotors")
	@ResponseBody
	public List<MotorDriver> getMotors(){
		return CommonCore.motorDrivers;
	}
	
	@RequestMapping(value = "/motorMove")
	@ResponseBody
	public void motorMove(@RequestParam("moterNum") Integer motorNum,@RequestParam("direction") Integer direction) throws InterruptedException{
	
		for(MotorDriver md : CommonCore.motorDrivers){
			if(md.getMotorNum()==motorNum){
				
			}
			System.out.println("----马达编号："+md.getMotorNum());
		}
		
	System.out.println("turnForward");
	Element element = new Element("run", true);  
	cacheManager.getCache("raspTest").put(element);
//		md.setForword();
//		Thread.sleep(5);
//		for(int i=10;i>1;i--){
//			md.setPul(i);
//		}
	
		while(cacheManager.getCache("raspTest").get("run")!=null&&Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run").getObjectValue().toString())){
			System.out.println("-------------forward");
//			md.setPul(1);
		}
		System.out.println("==========forward finished!");
	}
	
	
	@RequestMapping(value = "/turnForward")
	@ResponseBody
	public void turnForward() throws InterruptedException{
	System.out.println("turnForward");
	Element element = new Element("run", true);  
	cacheManager.getCache("raspTest").put(element);
//		md.setForword();
//		Thread.sleep(5);
//		for(int i=10;i>1;i--){
//			md.setPul(i);
//		}
	
		while(cacheManager.getCache("raspTest").get("run")!=null&&Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run").getObjectValue().toString())){
			System.out.println("-------------forward");
//			md.setPul(1);
		}
		System.out.println("==========forward finished!");
	}
	
	//定义一个方法，确认电动机在转动
	private void motorRun(){
		Element elementRun = new Element("run",true);
		cacheManager.getCache("raspTest").put(elementRun);
		
		while(cacheManager.getCache("raspTest").get("run")!=null&&Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run").getObjectValue().toString())){
//			md.setPul(1);
		}
	}
	
	@RequestMapping(value = "/turnBackword")
	@ResponseBody
	public void turnBackword() throws InterruptedException{
		System.out.println("turnBackword");
		Element element = new Element("run", true);  
		cacheManager.getCache("raspTest").put(element);
//			md.setBackword();
//			Thread.sleep(5);
//			for(int i=10;i>1;i--){
//				md.setPul(i);
//			}
			while(cacheManager.getCache("raspTest").get("run")!=null&&Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run").getObjectValue().toString())){
				System.out.println("-------------backward");
				Integer mills = (Integer) cacheManager.getCache("raspTest").get("mills").getObjectValue();
//				md.setPul(mills);
			}
			
			System.out.println("==========backward finished!");
	}
	
	
	@RequestMapping(value = "/turnPuase")
	@ResponseBody
	public void turnPuase(){
		System.out.println("turnPuase");
		Element element = new Element("run", false);  
		cacheManager.getCache("raspTest").put(element);
	}*/
}
