package com.miao.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miao.test.bean.Access;
import com.miao.test.mapper.AccessMapper;
import com.miao.test.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	private AccessMapper accessMapper;
	
	@Override
	public List<Access> getAccessByDronId(Integer droneId) {
		return accessMapper.getAccessByDronId(droneId);
	}

	@Override
	public void insertAccessForDrone(Access access) {
		accessMapper.insertAccessForDrone(access);
	}

	@Override
	public void deleteAccess(Integer id) {
		accessMapper.deleteAccess(id);
	}

	@Override
	public void updateAccess(Access access) {
		accessMapper.updateAccess(access);
	}

	@Override
	public Access getById(Integer id) {
		return accessMapper.getById(id);
	}

}
