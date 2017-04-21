package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.RaspClient;

public interface RaspclientMapper {
	void insertRaspclient(RaspClient raspclient);
	void deleteRaspclient(@Param("id") Integer id);
	List<RaspClient> getRaspclients();
	RaspClient getById(@Param("id") Integer id);
}
