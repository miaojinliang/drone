package com.miao.test.service;

import java.util.List;


import com.miao.test.bean.AutoPlan;
import com.miao.test.bean.PlanDetail;

public interface AutoPlanService {
	void insertAutoPlan(AutoPlan autoPlan);
	void deleteAutoPlan(Integer id);
	List<AutoPlan> getAutoPlans();
	AutoPlan getPlanById(Integer id);
	void updateAutoPlan(AutoPlan autoPlan);
	void insertPlanDetail(PlanDetail planDetail);
	void deletePlanDetail(Integer id);
	List<PlanDetail> getPlanDetails(Integer planId);
	PlanDetail getDetailById(Integer id);
}
