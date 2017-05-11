package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.AutoPlan;
import com.miao.test.bean.PlanDetail;

public interface PlanDetailMapper {
	void insertPlanDetail(PlanDetail planDetail);
	void deletePlanDetail(@Param("id") Integer id);
	List<PlanDetail> getPlanDetails();
	PlanDetail getById(@Param("id") Integer id);
}
