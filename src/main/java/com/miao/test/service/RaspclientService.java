package com.miao.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.RaspClient;

public interface RaspclientService {
	void insertRaspclient(RaspClient raspclient);
	void deleteRaspclient(Integer id);
	List<RaspClient> getRaspclients();
	RaspClient getRaspById(Integer id);
}
