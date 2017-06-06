package com.miao.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miao.test.bean.AutoPlan;
import com.miao.test.bean.PlanDetail;
import com.miao.test.mapper.AutoPlanMapper;
import com.miao.test.mapper.PlanDetailMapper;
import com.miao.test.service.AutoPlanService;

@Service
public class AutoPlanServiceImpl implements AutoPlanService {
	@Autowired
	private AutoPlanMapper autoPlanMapper;
	
	@Autowired
	private PlanDetailMapper planDetailMapper;

	@Override
	public void insertAutoPlan(AutoPlan autoPlan) {
		autoPlanMapper.insertAutoPlan(autoPlan);
	}

	@Override
	public void deleteAutoPlan(Integer id) {
		planDetailMapper.deletePlanDetailByPlanId(id);
		autoPlanMapper.deleteAutoPlan(id);
	}

	@Override
	public List<AutoPlan> getAutoPlans() {
		return autoPlanMapper.getAutoPlans();
	}

	@Override
	public AutoPlan getPlanById(Integer id) {
		return autoPlanMapper.getById(id);
	}

	@Override
	public void updateAutoPlan(AutoPlan autoPlan) {
		autoPlanMapper.updateAutoPlan(autoPlan);
	}

	@Override
	public void insertPlanDetail(PlanDetail planDetail) {
		planDetailMapper.insertPlanDetail(planDetail);
	}

	@Override
	public void deletePlanDetail(Integer id) {
		planDetailMapper.deletePlanDetail(id);
	}

	@Override
	public List<PlanDetail> getPlanDetails(Integer planId) {
	
		return planDetailMapper.getPlanDetails(planId);
	}

	@Override
	public PlanDetail getDetailById(Integer id) {
		return planDetailMapper.getById(id);
	}
	
	
}
