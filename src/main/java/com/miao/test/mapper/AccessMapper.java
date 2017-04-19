package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.Access;

public interface AccessMapper {
	List<Access> getAccessByDronId(@Param("droneId") Integer droneId);
	void insertAccessForDrone(Access access);
	void deleteAccess(@Param("id") Integer id);
	void updateAccess(Access access);
	Access getById(@Param("id") Integer id);
}
