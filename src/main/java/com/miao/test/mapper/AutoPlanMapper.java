package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.AutoPlan;
import com.miao.test.bean.RaspClient;

public interface AutoPlanMapper {
	void insertAutoPlan(AutoPlan autoPlan);
	void deleteAutoPlan(@Param("id") Integer id);
	List<AutoPlan> getAutoPlans();
	AutoPlan getById(@Param("id") Integer id);
	void updateAutoPlan(AutoPlan autoPlan);
}
