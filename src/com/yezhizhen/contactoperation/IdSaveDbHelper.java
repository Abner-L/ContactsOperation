package com.yezhizhen.contactoperation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class IdSaveDbHelper extends SQLiteOpenHelper {

	public IdSaveDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase sb) {
		sb.execSQL("create table contacts_id (_id integer primary key autoincrement not null , contact_id integer(20))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase sb, int arg1, int arg2) {
		
	}

}
