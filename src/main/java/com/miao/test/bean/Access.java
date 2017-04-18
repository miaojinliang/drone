package com.miao.test.bean;

/**
 * 定义接近开关
 * @author Miao jinliang
 *
 */
public class Access {
	private Integer id;//主键id
	private String name;//名称
	private Integer type;//类型1：停止开关；2：返回开关；3：旋转停止；4：旋转返回
	private Long backDelay;//返回时间(秒)
	private Integer droneId;//匹配电机
	private Integer openStatus;//开关常开类型：1：高电平常开（低电平触发动作）；2：低电平常开（高电平触发动作）
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getBackDelay() {
		return backDelay;
	}
	public void setBackDelay(Long backDelay) {
		this.backDelay = backDelay;
	}
	public Integer getDroneId() {
		return droneId;
	}
	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}
	public Integer getOpenStatus() {
		return openStatus;
	}
	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}
	
	
}
