package com.miao.test.driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.miao.test.bean.Access;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class DroneDriver {
	private GpioPinDigitalOutput pin_dir;//转向pin
	private GpioPinDigitalOutput pin_pul;//转速pin
	
	private Integer type;//电机类型1:步进电机；2：继电器电机;3：步进点击+继电器点击
	private Long startDelay;//起步延迟时间(秒)
	
	private Integer motorNum;// 马达id
	private Integer motorDirection;// 步进点击转向，0 正方向，1反方向
	private Integer relayDirection;//继电器点击转动方向，0：正向，1：反向
	
	private Integer interval = 5;// 脉冲频率，毫秒
	private Boolean motorRunning = false;// 步进电机马达状态，false：暂停；true：转动（转动的方向取自direction）
	private Boolean relayRunning = false;//继电器电机马达状态 false：暂停；true：转动

	private GpioPinDigitalOutput pin_before;//正转
	private GpioPinDigitalOutput pin_back;//反转
	private Long rotateDelay;//靶机旋转延迟时间

//	private GpioStepperMotorControl controlThread = new GpioStepperMotorControl();

	public static final int FORWARD = 0;// 定义方向为正向
	public static final int BACKWARD = 1;// 定义方向反向

	private static final int speeds[] = { 1000, 900, 800, 10000, 5000, 1000, 500, 100, 50, 10, 1 };

//	private GpioPinListenerDigital backListener = new GpioPinListenerDigital() {
//		@Override
//		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//			if (event.getState().isHigh()) {
//				if(running){
//					running = false;
//					try {
//						Thread.sleep(delayTime);
//						if(pin_dir.isHigh()){
//							pin_dir.setState(PinState.LOW);
//						}else{
//							pin_dir.setState(PinState.HIGH);
//						}
//						running = true;
//						move();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//	};

//	private GpioPinListenerDigital stopListener = new GpioPinListenerDigital() {
//
//		@Override
//		public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//			if (event.getState().isHigh()) {
//				if(running){
//					running = false;
//				}
//			}
//		}
//
//	};
	
	/**
	 * 无参构造器
	 */
	public DroneDriver() {
	}
	
	/**
	 * 初始化构造器
	 * @param id
	 * @param type
	 * @param startDelay
	 * @param dirPin
	 * @param pulPin
	 * @param interval
	 * @param beforePin
	 * @param backPin
	 * @param rotateDelay
	 * @param accesses
	 */
	public DroneDriver(Integer id, Integer type, Long startDelay,
			Integer dirPin, Integer pulPin, Integer interval,
			Integer beforePin, Integer backPin, Long rotateDelay,List<Access> accesses) {
		this.motorNum = id;
		this.startDelay = startDelay;
		this.interval = interval;
		this.rotateDelay = rotateDelay;
		this.type=type;
		final GpioController gpio = GpioFactory.getInstance();
		
		switch (type) {
		case 1://单步进电机
			initMotorDriver(dirPin, pulPin, gpio);
			break;
		case 2://继电器电机
			intiRelayDriver(beforePin, backPin, gpio);
			break;
		case 3://步进电机+继电器点击（小车）
			initMotorDriver(dirPin, pulPin, gpio);
			intiRelayDriver(beforePin, backPin, gpio);
			break;
		default:
			break;
		}
		//初始化停止开关
		initAccess(accesses, gpio);
	}
	
	private GpioPinListenerDigital getMotorStopListener(final Integer openStatus){
		GpioPinListenerDigital stopListener = new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(openStatus==2){
					if (event.getState().isHigh()) {
						if(motorRunning){
							motorRunning = false;
						}
					}
				}else if(openStatus==1){
					if (event.getState().isLow()) {
						if(motorRunning){
							motorRunning = false;
						}
					}
				}
				
			}

		};
		return stopListener;
	}
	
	
	private GpioPinListenerDigital getMotorBackListener(final Long delayTime,final Integer openStatus){
		GpioPinListenerDigital backListener = new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				
				if(openStatus==2){
					if (event.getState().isHigh()) {
						motorBack(delayTime);
					}
				}else if(openStatus==1){
					if (event.getState().isLow()) {
						motorBack(delayTime);
					}
				}
			}
		};
		return backListener;
	}
	
	/**
	 * 步进点击反向运动
	 * @param delayTime
	 */
	private void motorBack(Long delayTime){
		if(motorRunning){
			motorRunning = false;
			try {
				Thread.sleep(delayTime);
				if(pin_dir.isHigh()){
					pin_dir.setState(PinState.LOW);
				}else{
					pin_dir.setState(PinState.HIGH);
				}
				motorRunning = true;
				motorStart();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private GpioPinListenerDigital getRelayStopListener(final Integer openStatus){
		GpioPinListenerDigital stopListener = new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(openStatus==2){
					if (event.getState().isHigh()) {
//						stopRelay();
						relayStop();
					}
				}else if(openStatus==1){
					if (event.getState().isLow()) {
//						stopRelay();
						relayStop();
					}
				}
			}

		};
		return stopListener;
	}
	
	
	private GpioPinListenerDigital getRelayBackListener(final Long backDelay,final Integer openStatus){
		GpioPinListenerDigital backListener = new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				
				if(openStatus==2){
					if (event.getState().isHigh()) {
						backRelay(backDelay);
					}
				}else if(openStatus==1){
					if (event.getState().isLow()) {
						backRelay(backDelay);
					}
				}
			}
		};
		return backListener;
	}
	
	/**
	 * 继电器电机停止
	 */
	private void stopRelay(){
		if(relayDirection==0){//正向
			pin_before.setState(PinState.HIGH);
		}else{
			pin_back.setState(PinState.HIGH);
		}
		//保障一下，都停
		pin_before.setState(PinState.HIGH);
		pin_back.setState(PinState.HIGH);
	}
	
	/**
	 * 继电器电机反向运动
	 * @param backDelay
	 */
	private void backRelay(Long backDelay){
		relayStop();
		try {
			Thread.sleep(backDelay);
			if(relayDirection==0){
				//原属性是正向，本次就是反向，并更新属性
				pin_back.setState(PinState.LOW);
				relayDirection=1;
			}else{
				//原属性是反向，本次就是正向，并更新属性
				pin_before.setState(PinState.LOW);
				relayDirection=0;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
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

	
	
	/**
	 * 初始化步进电机
	 * @param dirPin
	 * @param pulPin
	 * @param gpio
	 */
	private void initMotorDriver(Integer dirPin,Integer pulPin,final GpioController gpio){
		pin_dir = gpio.provisionDigitalOutputPin(getGPIONum(dirPin), "dirPin", PinState.HIGH);
		pin_pul = gpio.provisionDigitalOutputPin(getGPIONum(pulPin), "pulPin", PinState.HIGH);
		pin_dir.setShutdownOptions(true, PinState.LOW);
		pin_pul.setShutdownOptions(true, PinState.LOW);
	}
	
	/**
	 * 初始化继电器电机
	 * @param beforePin
	 * @param backPin
	 * @param gpio
	 */
	private void intiRelayDriver(Integer beforePin,Integer backPin,final GpioController gpio){
		pin_before = gpio.provisionDigitalOutputPin(getGPIONum(beforePin), "beforePin", PinState.HIGH);
		pin_back = gpio.provisionDigitalOutputPin(getGPIONum(backPin), "backPin", PinState.HIGH);
		pin_before.setShutdownOptions(true, PinState.LOW);
		pin_back.setShutdownOptions(true, PinState.LOW);
	}
	
	/**
	 * 初始化停止开关
	 * @param accesses
	 * @param type
	 * @param gpio
	 */
	private void initAccess(List<Access> accesses,final GpioController gpio){
		for(Access access : accesses){
			GpioPinDigitalInput stopPin = gpio.provisionDigitalInputPin(getGPIONum(access.getPinNum()));
			switch (access.getType()) {
			case 1:
				stopPin.addListener(getMotorStopListener(access.getOpenStatus()));
				break;
			case 2:
				stopPin.addListener(getMotorBackListener(access.getBackDelay(),access.getOpenStatus()));
				break;
			case 3:
				stopPin.addListener(getRelayStopListener(access.getOpenStatus()));
				break;
			case 4:
				stopPin.addListener(getRelayBackListener(access.getBackDelay(),access.getOpenStatus()));
				break;

			default:
				break;
			}
			
		}
	}
	
//
//	public MotorDriver(Integer dirPin, Integer pulPin, Integer motorNum, Integer backPin, Integer stopPin,
//			Long dtime) {
//		this.motorNum = motorNum;
//		this.delayTime = dtime;
//		final GpioController gpio = GpioFactory.getInstance();
//		pin_dir = gpio.provisionDigitalOutputPin(getGPIONum(dirPin), "dirPin", PinState.HIGH);
//		pin_pul = gpio.provisionDigitalOutputPin(getGPIONum(pulPin), "pulPin", PinState.HIGH);
//		// 停止接近开关
//		pin_stop = gpio.provisionDigitalInputPin(getGPIONum(stopPin));
//		// 顶点接近开关
//		pin_back = gpio.provisionDigitalInputPin(getGPIONum(backPin));
//		pin_back.setShutdownOptions(true, PinState.LOW);
//		pin_back.addListener(backListener);
//
//		pin_stop.setShutdownOptions(true, PinState.LOW);
//		pin_stop.addListener(stopListener);
//
//		pin_dir.setShutdownOptions(true, PinState.LOW);
//		pin_pul.setShutdownOptions(true, PinState.LOW);
//
//	}

	/**
	 * 设置步进电机转动方向，手动直接改变。
	 * @param motorDirection
	 */
	public void setMotorRunningDirection(Integer motorDirection) {
		this.motorDirection = motorDirection;
		pin_pul.setState(PinState.LOW);
		if (motorDirection == FORWARD) {
			// System.out.println("马达编号【"+this.motorNum+"】设置方向---low："+FORWARD);
			pin_dir.low();
		} else if (motorDirection == BACKWARD) {
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

//	public void moveThread() {
//		if (this.direction == null) {
//			System.out.println("没有设置方向！");
//			return;
//		}
//		if (this.interval == null) {
//			System.out.println("没有设置转速！");
//			return;
//		}
//
////		if (!controlThread.isAlive()) {
////			controlThread = new GpioStepperMotorControl();
////			controlThread.start();
////		}
//	}

	// 转动
	/**
	 * 步进电机开始启动
	 * @throws InterruptedException
	 */
	public void motorStart() throws InterruptedException {
		try {
			if (motorDirection == null) {
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
			while (motorRunning) {
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

	/**
	 * 继电器靶机 正转
	 */
	public void relayStart(){
		pin_back.setState(PinState.HIGH);
		pin_before.setState(PinState.LOW);
	}
	
	/**
	 * 继电器靶机反转
	 */
	public void relayBack(){
		pin_before.setState(PinState.HIGH);
		pin_back.setState(PinState.LOW);
	}
	
	/**
	 * 继电器靶机停止
	 */
	public void relayStop(){
		pin_before.setState(PinState.HIGH);
		pin_back.setState(PinState.HIGH);
	}
	
	/**
	 * 按照圈数旋转，方法暂时没有用
	 * @param fen
	 * @throws InterruptedException
	 */
	@Deprecated
	public void moveCycle(Integer fen) throws InterruptedException {
		if (this.motorDirection == null) {
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

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}


	public Integer getMotorDirection() {
		return motorDirection;
	}


	public void setMotorDirection(Integer motorDirection) {
		this.motorDirection = motorDirection;
	}


	public Integer getRelayDirection() {
		return relayDirection;
	}


	public void setRelayDirection(Integer relayDirection) {
		this.relayDirection = relayDirection;
	}


	public Boolean getMotorRunning() {
		return motorRunning;
	}


	public void setMotorRunning(Boolean motorRunning) {
		this.motorRunning = motorRunning;
	}


	public Boolean getRelayRunning() {
		return relayRunning;
	}


	public void setRelayRunning(Boolean relayRunning) {
		this.relayRunning = relayRunning;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
