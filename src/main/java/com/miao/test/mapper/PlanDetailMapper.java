package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.AutoPlan;
import com.miao.test.bean.PlanDetail;

public interface PlanDetailMapper {
	void insertPlanDetail(PlanDetail planDetail);
	void deletePlanDetail(@Param("id") Integer id);
	List<PlanDetail> getPlanDetails(@Param("planId") Integer planId);
	PlanDetail getById(@Param("id") Integer id);
	void deletePlanDetailByPlanId(@Param("planId") Integer planId);
}
