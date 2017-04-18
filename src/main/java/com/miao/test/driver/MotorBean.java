package com.miao.test.driver;

/**
 * 马达bean，用于接收配置文件信息并转换为初始化MotorDriver
 * @author admin
 *
 */
public class MotorBean {
	private Integer motorNum;
	private Integer dirPin;
	private Integer pulPin;
	private Integer backPin;
	private Integer stopPin;
	private Long delayTime;
	public Integer getMotorNum() {
		return motorNum;
	}
	public void setMotorNum(Integer motorNum) {
		this.motorNum = motorNum;
	}
	public Integer getDirPin() {
		return dirPin;
	}
	public void setDirPin(Integer dirPin) {
		this.dirPin = dirPin;
	}
	public Integer getPulPin() {
		return pulPin;
	}
	public void setPulPin(Integer pulPin) {
		this.pulPin = pulPin;
	}
	public Integer getBackPin() {
		return backPin;
	}
	public void setBackPin(Integer backPin) {
		this.backPin = backPin;
	}
	public Integer getStopPin() {
		return stopPin;
	}
	public void setStopPin(Integer stopPin) {
		this.stopPin = stopPin;
	}
	public Long getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Long delayTime) {
		this.delayTime = delayTime;
	}
	
	
	
}
