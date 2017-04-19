package com.miao.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.Access;

public interface AccessService {
	List<Access> getAccessByDronId(Integer droneId);
	void insertAccessForDrone(Access access);
	void deleteAccess(Integer id);
	void updateAccess(Access access);
	Access getById(Integer id);
}
