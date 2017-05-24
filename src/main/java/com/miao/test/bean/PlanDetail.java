package com.miao.test.bean;

/**
 * 自动运行方案详情
 * @author Miao jinliang
 *
 */
public class PlanDetail {
	private Integer id;//主键
	private Integer droneId;//靶机id
	private Integer raspId;//分机树莓派id
	private Integer sortNum;//执行顺序
	private Integer planId;//所属计划id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDroneId() {
		return droneId;
	}
	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}
	public Integer getRaspId() {
		return raspId;
	}
	public void setRaspId(Integer raspId) {
		this.raspId = raspId;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
}
