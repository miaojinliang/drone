package com.miao.test.bean;

import java.util.Date;

/**
 * 自动运行方案bean
 * @author Miao jinliang
 *
 */
public class AutoPlan {
	private Integer id;//主键
	private String name;//自动方案名称
	private Date createTime;//创建时间
	private String remark;//自动方案备注
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
