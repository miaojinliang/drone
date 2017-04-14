package com.miao.test.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinAnalogValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerAnalog;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class MotorDriver {
	private GpioPinDigitalOutput pin_dir;
	private GpioPinDigitalOutput pin_pul;
	private GpioPinDigitalInput pin_back;// 返回运动开关
	private GpioPinDigitalInput pin_stop;// 停止运动开关
	private Long delayTime;
	private Integer motorNum;// 马达编号
	private Integer direction;// 转向，0 正方向，1反方向
	private Integer interval = 5;// 脉冲频率，毫秒
	private Boolean running = false;// 马达状态，false：暂停；true：转动（转动的方向取自direction）

	private Integer cycly = 200;

//	private GpioStepperMotorControl controlThread = new GpioStepperMotorControl();

	public static final int FORWARD = 0;// 定义方向为正向
	public static final int BACKWARD = 1;// 定义方向反向

	private static final int speeds[] = { 1000, 900, 800, 10000, 5000, 1000, 500, 100, 50, 10, 1 };

	private GpioPinListenerDigital backListener = new GpioPinListenerDigital() {
		@Override
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			if (event.getState().isHigh()) {
				if(running){
					running = false;
					try {
						Thread.sleep(delayTime);
						if(pin_dir.isHigh()){
							pin_dir.setState(PinState.LOW);
						}else{
							pin_dir.setState(PinState.HIGH);
						}
						running = true;
						move();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};

	private GpioPinListenerDigital stopListener = new GpioPinListenerDigital() {

		@Override
		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			if (event.getState().isHigh()) {
				if(running){
					running = false;
				}
			}
		}

	};

	// private GpioPinListenerAnalog pinListener = new GpioPinListenerAnalog() {
	//
	// @Override
	// public void
	// handleGpioPinAnalogValueChangeEvent(GpioPinAnalogValueChangeEvent event)
	// {
	// // notify any sensor change listeners
	//
	// System.out.println("-----------"+event.getValue());
	// }
	// };

	public MotorDriver() {
	}

	public MotorDriver(Integer dirPin, Integer pulPin, Integer motorNum, Integer backPin, Integer stopPin,
			Long dtime) {
		this.motorNum = motorNum;
		this.delayTime = dtime;
		final GpioController gpio = GpioFactory.getInstance();
		pin_dir = gpio.provisionDigitalOutputPin(getGPIONum(dirPin), "dirPin", PinState.HIGH);
		pin_pul = gpio.provisionDigitalOutputPin(getGPIONum(pulPin), "pulPin", PinState.HIGH);
		// 停止接近开关
		pin_stop = gpio.provisionDigitalInputPin(getGPIONum(stopPin));
		// 顶点接近开关
		pin_back = gpio.provisionDigitalInputPin(getGPIONum(backPin));
		pin_back.setShutdownOptions(true, PinState.LOW);
		pin_back.addListener(backListener);

		pin_stop.setShutdownOptions(true, PinState.LOW);
		pin_stop.addListener(stopListener);

		pin_dir.setShutdownOptions(true, PinState.LOW);
		pin_pul.setShutdownOptions(true, PinState.LOW);

	}

	// 设置马达转动方向
	public void setDirection(Integer direction) {
		this.direction = direction;
		pin_pul.setState(PinState.LOW);
		if (direction == FORWARD) {
			// System.out.println("马达编号【"+this.motorNum+"】设置方向---low："+FORWARD);
			pin_dir.low();
		} else if (direction == BACKWARD) {
			// System.out.println("马达编号【"+this.motorNum+"】设置方向---high："+BACKWARD);
			pin_dir.high();
		}
	}

//	private class GpioStepperMotorControl extends Thread {
//		public void run() {
//			try {
//				if (direction == null) {
//					System.out.println("没有设置方向！");
//					return;
//				}
//				if (interval == null) {
//					System.out.println("没有设置转速！");
//					return;
//				}
//				for (int i = 10; i > 1; i--) {
//					pin_pul.setState(PinState.HIGH);
//					Thread.sleep(i);
//					pin_pul.setState(PinState.LOW);
//					Thread.sleep(i);
//				}
//				long time = System.nanoTime();
//				while (running) {
//					long tmp = System.nanoTime();
//					if (tmp - time > (100000+interval*10000)) {
//						if (pin_pul.isHigh()) {
//							pin_pul.setState(PinState.LOW);
//						} else {
//							pin_pul.setState(PinState.HIGH);
//						}
//						time = tmp;
//					}
//				}
//				// }
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

	public void moveThread() {
		if (this.direction == null) {
			System.out.println("没有设置方向！");
			return;
		}
		if (this.interval == null) {
			System.out.println("没有设置转速！");
			return;
		}

//		if (!controlThread.isAlive()) {
//			controlThread = new GpioStepperMotorControl();
//			controlThread.start();
//		}
	}

	// 转动
	public void move() throws InterruptedException {
		try {
			if (direction == null) {
				System.out.println("没有设置方向！");
				return;
			}
			if (interval == null) {
				System.out.println("没有设置转速！");
				return;
			}
			long initTime =  System.nanoTime();
			int init = 6400;
			while(init >=0){
				long tmp = System.nanoTime();
				if (tmp - initTime > (35000+interval*10000+init*20)) {
//				if (tmp - initTime > (1000000)) {
					if (pin_pul.isHigh()) {
						pin_pul.setState(PinState.LOW);
					} else {
						pin_pul.setState(PinState.HIGH);
					}
					initTime = tmp;
					init--;
				}
			}
			long time = System.nanoTime();
			while (running) {
				long tmp = System.nanoTime();
				if (tmp - time > (35000+interval*10000)) {
					if (pin_pul.isHigh()) {
						pin_pul.setState(PinState.LOW);
					} else {
						pin_pul.setState(PinState.HIGH);
					}
					time = tmp;
				}
			}
			System.out.println("running ----finished!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveCycle(Integer fen) throws InterruptedException {
		if (this.direction == null) {
			System.out.println("没有设置方向！");
			return;
		}
		if (this.interval == null) {
			System.out.println("没有设置转速！");
			return;
		}
		for (int i = 0; i < 200 / fen; i++) {
			pin_pul.high();
			// Thread.sleep(interval);
			Thread.sleep(0, speeds[10 - interval]);
			// System.out.println("马达编号【"+this.motorNum+"】脉冲-频率【"+speeds[10-interval]+"】----high,方向："+(direction==0?"【正向】":"【反向】"+i));
			pin_pul.low();
			Thread.sleep(0, speeds[10 - interval]);
			// Thread.sleep(interval);
			// System.out.println("马达编号【"+this.motorNum+"】脉冲-频率【"+speeds[10-interval]+"】----low
			// ,方向："+(direction==0?"【正向】":"【反向】"+i));
		}
	}

	// 马达停止转动（方法暂时停用）
	public void stop() {
		System.out.println("马达编号【" + this.motorNum + "】暂停！");
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

	public static void main(String[] args) throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date st = new Date();
		System.out.println(sdf.format(st));
		for (int i = 0; i < 500; i++) {
			Thread.sleep(1);
			Thread.sleep(1);
		}
		Date sd = new Date();

		System.out.println(sdf.format(st));
	}

	public Integer getMotorNum() {
		return motorNum;
	}

	public void setMotorNum(Integer motorNum) {
		this.motorNum = motorNum;
	}

	public Integer getDirection() {
		return direction;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public void setRunning(Boolean running) {
		this.running = running;
	}

	public Boolean getRunning() {
		return running;
	}
}
