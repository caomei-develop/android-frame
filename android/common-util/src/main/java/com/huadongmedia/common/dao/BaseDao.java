package com.huadongmedia.common.dao;

import android.database.sqlite.SQLiteDatabase;

abstract public class BaseDao {

	private BaseDBHelper dbHelper;

	public BaseDao(BaseDBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	protected SQLiteDatabase getDb(boolean write) {
		return dbHelper.getDb(write);
	}

	abstract public void onCreate(SQLiteDatabase db);

	abstract public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
