package com.miao.test.bean;

/**
 * 定义靶机属性
 * @author Miao jinliang
 *
 */
public class Drone {
	private Integer id;//主键
	private String name;//靶机名称
	private Integer seq;//序号
	private Integer type;//电机类型1:步进电机；2：继电器电机;3：步进点击+继电器点击
	private Long startDelay;//起步延迟时间(秒)
	
	//如果电机类型是步进电机，有转速和方向，也可以当做小车的运动电机配置
	private Integer dirPin;//转动方向pin
	private Integer pulPin;//转速pin
	private Integer direction;//定义转向，0:正方向，1：反方向
	private Integer interval;//定义速度
	
	//定义普通继电器靶机
	private Integer beforePin;//正转
	private Integer backPin;//反转
	
	//小车+旋转靶机的情况下，会有一个旋转延迟属性，用于小车运动后多长时间靶机开始旋转
	private Long rotateDelay;//靶机旋转延迟属性
	
	
	//设置按钮的名称
	
	private String startButton;//定义开始按钮的名称
	private String backButton;//定义反向按钮的名称
	private String stopButton;//定义暂停按钮的名称
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getStartDelay() {
		return startDelay;
	}

	public void setStartDelay(Long startDelay) {
		this.startDelay = startDelay;
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

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getBeforePin() {
		return beforePin;
	}

	public void setBeforePin(Integer beforePin) {
		this.beforePin = beforePin;
	}

	public Integer getBackPin() {
		return backPin;
	}

	public void setBackPin(Integer backPin) {
		this.backPin = backPin;
	}

	public Long getRotateDelay() {
		return rotateDelay;
	}

	public void setRotateDelay(Long rotateDelay) {
		this.rotateDelay = rotateDelay;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public String getStartButton() {
		return startButton;
	}

	public void setStartButton(String startButton) {
		this.startButton = startButton;
	}

	public String getBackButton() {
		return backButton;
	}

	public void setBackButton(String backButton) {
		this.backButton = backButton;
	}

	public String getStopButton() {
		return stopButton;
	}

	public void setStopButton(String stopButton) {
		this.stopButton = stopButton;
	}
	
}
