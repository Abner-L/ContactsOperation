package com.yezhizhen.contactoperation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class IdSaveDbHelper extends SQLiteOpenHelper {
	/**
	 * 这个一个数据库的帮助类  提供创建一个数据库 用于存储新建的联系人的ID 可以根据存储的ID删除新建的联系人 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	
	private IdSaveDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	private static IdSaveDbHelper idSaveDbHelper = null;

	@Override
	public void onCreate(SQLiteDatabase sb) {
		sb.execSQL("create table contacts_id (_id integer primary key autoincrement not null , contact_id integer(20))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase sb, int arg1, int arg2) {

	}
	
	// 定义一个提供获取实例的方法
	public static IdSaveDbHelper getHelper(Context context) {
		idSaveDbHelper = idSaveDbHelper == null ? new IdSaveDbHelper(context,
				"contacts_id", null, 1) : idSaveDbHelper;
		return idSaveDbHelper;
	}

	// 定义一个方法 保存联系人的contact_id
	public void saveContactId(long rawContactID, IdSaveDbHelper idSaveDbHelper) {

		idSaveDbHelper.getWritableDatabase().execSQL(
				"insert into contacts_id values(null , ?)",
				new Object[] { rawContactID });
		idSaveDbHelper.close();
	}
}
