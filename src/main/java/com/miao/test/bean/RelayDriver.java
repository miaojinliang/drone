package com.miao.test.bean;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class RelayDriver {
	private GpioPinDigitalOutput pin_before;
	private GpioPinDigitalOutput pin_back;
	private GpioPinDigitalInput pin_stop1;// 停止运动开关
	private GpioPinDigitalInput pin_stop2;// 停止运动开关
	
	
	private GpioPinListenerDigital stopListener = new GpioPinListenerDigital() {

		@Override
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			if (event.getState().isHigh()) {
				if(pin_before.isLow()){
					pin_before.setState(PinState.HIGH);
				}
				
				if(pin_back.isLow()){
					pin_back.setState(PinState.HIGH);
				}
			}
		}

	};
	
	public RelayDriver(){
		
	}
	
	public RelayDriver(Integer beforePin,Integer backPin,Integer stop1Pin,Integer stop2Pin){
		final GpioController gpio = GpioFactory.getInstance();
		pin_before = gpio.provisionDigitalOutputPin(getGPIONum(beforePin), "beforePin", PinState.HIGH);
		pin_back = gpio.provisionDigitalOutputPin(getGPIONum(backPin), "backPin", PinState.HIGH);
		
		pin_stop1 = gpio.provisionDigitalInputPin(getGPIONum(stop1Pin));
		
		pin_stop2 = gpio.provisionDigitalInputPin(getGPIONum(stop2Pin));
		
		
		pin_before.setShutdownOptions(true, PinState.LOW);
		pin_back.setShutdownOptions(true, PinState.LOW);
		pin_stop1.setShutdownOptions(true, PinState.LOW);
		pin_stop2.setShutdownOptions(true, PinState.LOW);
		
		pin_stop1.addListener(stopListener);
		
		pin_stop2.addListener(stopListener);
		
	}
	
	
	public void runBefore(){
		pin_back.setState(PinState.HIGH);
		pin_before.setState(PinState.LOW);
	}
	
	public void runBack(){
		pin_before.setState(PinState.HIGH);
		pin_back.setState(PinState.LOW);
	}

	public void stopAll(){
		pin_before.setState(PinState.HIGH);
		pin_back.setState(PinState.HIGH);
	}
	
	private Pin getGPIONum(Integer pinNum) {
		Pin resPin;
		switch (pinNum) {
		case 0:
			resPin = RaspiPin.GPIO_00;
			break;
		case 1:
			resPin = RaspiPin.GPIO_01;
			break;
		case 2:
			resPin = RaspiPin.GPIO_02;
			break;
		case 3:
			resPin = RaspiPin.GPIO_03;
			break;
		case 4:
			resPin = RaspiPin.GPIO_04;
			break;
		case 5:
			resPin = RaspiPin.GPIO_05;
			break;
		case 6:
			resPin = RaspiPin.GPIO_06;
			break;
		case 7:
			resPin = RaspiPin.GPIO_07;
			break;
		case 8:
			resPin = RaspiPin.GPIO_08;
			break;
		case 9:
			resPin = RaspiPin.GPIO_09;
			break;
		case 10:
			resPin = RaspiPin.GPIO_10;
			break;
		case 11:
			resPin = RaspiPin.GPIO_11;
			break;
		case 12:
			resPin = RaspiPin.GPIO_12;
			break;
		case 13:
			resPin = RaspiPin.GPIO_13;
			break;
		case 14:
			resPin = RaspiPin.GPIO_14;
			break;
		case 15:
			resPin = RaspiPin.GPIO_15;
			break;
		case 16:
			resPin = RaspiPin.GPIO_16;
			break;
		case 17:
			resPin = RaspiPin.GPIO_17;
			break;
		case 18:
			resPin = RaspiPin.GPIO_18;
			break;
		case 19:
			resPin = RaspiPin.GPIO_19;
			break;
		case 20:
			resPin = RaspiPin.GPIO_20;
			break;
		case 21:
			resPin = RaspiPin.GPIO_21;
			break;
		case 22:
			resPin = RaspiPin.GPIO_22;
			break;
		case 23:
			resPin = RaspiPin.GPIO_23;
			break;
		case 24:
			resPin = RaspiPin.GPIO_24;
			break;
		case 25:
			resPin = RaspiPin.GPIO_25;
			break;
		case 26:
			resPin = RaspiPin.GPIO_26;
			break;
		case 27:
			resPin = RaspiPin.GPIO_27;
			break;
		case 28:
			resPin = RaspiPin.GPIO_28;
			break;
		case 29:
			resPin = RaspiPin.GPIO_29;
			break;
		default:
			resPin = RaspiPin.GPIO_00;
			break;
		}
		return resPin;
	}	
}
