package com.miao.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miao.test.bean.RaspClient;
import com.miao.test.mapper.RaspclientMapper;
import com.miao.test.service.RaspclientService;

@Service
public class RaspclientServiceImpl implements RaspclientService{
	
	@Autowired
	private RaspclientMapper raspclientMapper;
	
	@Override
	public void insertRaspclient(RaspClient raspclient) {
		raspclientMapper.insertRaspclient(raspclient);
	}

	@Override
	public void deleteRaspclient(Integer id) {
		raspclientMapper.deleteRaspclient(id);
	}

	@Override
	public List<RaspClient> getRaspclients() {
		return raspclientMapper.getRaspclients();
	}

	@Override
	public RaspClient getRaspById(Integer id) {
		return raspclientMapper.getById(id);
	}

}
