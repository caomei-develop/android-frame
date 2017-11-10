package com.huadongmedia.common.dao;

import java.util.List;

abstract public class BaseDaoFactory {

	public <T extends BaseDao> T get(Class<T> cls) {
		for (BaseDBHelper dbHelper : getDBHelperList()) {
			T dao = dbHelper.indexOf(cls);
			if (dao != null) {
				return dao;
			}
		}
		return null;
	}

	abstract protected List<BaseDBHelper> getDBHelperList();
}
