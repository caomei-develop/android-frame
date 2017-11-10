package com.huadongmedia.common.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

abstract public class BaseDBHelper extends SQLiteOpenHelper {

	private List<BaseDao> daoList = new ArrayList<>();

	public BaseDBHelper(Context context, String dbName, int dbVersion) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (BaseDao baseDao : daoList) {
			baseDao.onCreate(db);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (BaseDao baseDao : daoList) {
			baseDao.onUpgrade(db, oldVersion, newVersion);
		}
	}

	public SQLiteDatabase getDb(boolean write) {
		if (write) {
			return this.getWritableDatabase();
		} else {
			return this.getReadableDatabase();
		}
	}

	protected void addDao(BaseDao dao) {
		daoList.add(dao);
	}

	public <T extends BaseDao> T indexOf(Class<T> cls) {
		for (BaseDao dao : daoList) {
			if (cls.isInstance(dao)) {
				return (T) dao;
			}
		}
		return null;
	}
}
