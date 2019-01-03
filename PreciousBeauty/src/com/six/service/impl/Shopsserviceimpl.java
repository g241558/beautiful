package com.six.service.impl;

import java.util.List;
import java.util.Map;

import com.six.dao.Shopsdao;
import com.six.dao.impl.Shopsdaoimpl;
import com.six.service.Shopsservice;

public class Shopsserviceimpl implements Shopsservice {

	Shopsdao s=new Shopsdaoimpl();
	@Override
	public List<Map<String, Object>> getAllshop() {
		// TODO Auto-generated method stub
		return s.getAllshop();
	}

}
