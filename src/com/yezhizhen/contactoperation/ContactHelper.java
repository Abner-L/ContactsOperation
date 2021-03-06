package com.yezhizhen.contactoperation;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.widget.Toast;

public class ContactHelper {
	/**
	 * 本类中实现的是 保存联系人 删除联系人的方法
	 */
	long rawContactID;
	ContentValues values;
	Context context;
	IdSaveDbHelper dbHelper;
	ContentResolver contentResolver;

	public static ContactHelper mContactHelper = null;
	private ContactHelper(Context context) {
		super();
		this.context = context;
		dbHelper = IdSaveDbHelper.getHelper(context);
		contentResolver= context.getContentResolver();
	}

	public static synchronized ContactHelper getContactHelper(Context context) {
		if (mContactHelper == null) {
			mContactHelper = new ContactHelper(context);
		}
		return mContactHelper;
	}

	// 定义一个方法 保存联系人信息
	
	public void saveContactInfo() {
		
		FieldResources fr = new FieldResources();
		Random random = new Random();
		values = new ContentValues();
		Uri rawContactUri = contentResolver.insert(RawContacts.CONTENT_URI,values);
				
		rawContactID = ContentUris.parseId(rawContactUri);
		values.clear();
		// 1.保存联系人的姓名 ok
		if (random.nextInt(2) == 1) {
			
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
			values.put(StructuredName.GIVEN_NAME, fr.name);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 3.保存联系人的昵称 ok
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, fr.nickname);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 4.保存联系人的电话 ok
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			values.put(Phone.NUMBER, fr.phone);
			values.put(Phone.TYPE, Phone.TYPE_MOBILE);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 5.保存联系人的email ok
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, fr.email);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 6.保存联系人的网址 ok
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, fr.website);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 7.保存联系人的地址 ok
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/postal-address_v2");
			values.put(Data.DATA1, fr.address);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 8.保存联系人的组织信息 ok
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, fr.orgInfo);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 9.保存联系人的event
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, fr.event);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		// 10.保存联系人的即时信息
		if (random.nextInt(2) == 1) {
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, fr.timelyInfo);
			contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		}
		//设置头像
		updateAvatar(context);
		// 存储联系人的id
		dbHelper.getWritableDatabase().execSQL("insert into contacts_id values(null , ?)",new Object[] { rawContactID });
		dbHelper.close();

	}

	// 定义一个方法存储联系人到头像到数据库
	public void updateAvatar(Context context) {
		Random photoName = new Random();
		int i = photoName.nextInt(10);
		Bitmap avatarBM = BitmapFactory.decodeResource(context.getResources(),R.drawable.photo1 + i);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		avatarBM.compress(Bitmap.CompressFormat.PNG, 100, os);
		byte[] avatar = os.toByteArray();
		Log.e("aa", "id 为－"+rawContactID + "的联系人被创建了");
		values.put(Data.RAW_CONTACT_ID, rawContactID);
		values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
		values.put(Photo.PHOTO, avatar);
		contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
		values.clear();
	}

	// 根据数据库中的id删除联系人
	public void deleteContactId() {
		Cursor idCursor = dbHelper.getReadableDatabase().rawQuery("select * from contacts_id where contact_id > 0", null);
		StringBuilder sb = new StringBuilder();
		sb.append("id 为---");
		
		while (idCursor.moveToNext()) {
			String id = idCursor.getString(idCursor.getColumnIndex("contact_id"));
	
			String where = ContactsContract.Data._ID + " =?";
			String[] whereparams = new String[]{id};
			contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, where, whereparams);
			dbHelper.getWritableDatabase().execSQL("delete from contacts_id where contact_id = ?", new Object[]{id});
			sb.append(id + "---");
			
		}
		Toast.makeText(context, "删除成功",   Toast.LENGTH_SHORT).show();
		sb.append("的联系人被删除了");
		String msg = sb.toString();
		Log.e("contactsid", msg);
	}
	
}