package com.six.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.six.dao.Shopsdao;
import com.six.entity.Shops;
import com.six.entity.Type;
import com.six.until.BaseDao;

public class Shopsdaoimpl extends BaseDao<Map<String, Object>> implements Shopsdao{

	//查询所有商品
	@Override
	public List<Map<String, Object>> getAllshop() {
		String sql="SELECT shid, shname,shprice,shnum,tname FROM shops,TYPE WHERE shops.`tid`=type.`tid`";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet rs=null;
		try {
			rs = this.executeQuery(sql, null);
			while (rs!=null&&rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("shname", rs.getString("shname"));
				m.put("tname", rs.getString("tname"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return  list;
	}

}







