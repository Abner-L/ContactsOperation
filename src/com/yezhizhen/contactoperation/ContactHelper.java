package com.yezhizhen.contactoperation;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class ContactHelper {
	long rawContactID;
	ContentValues	values ;
	IdSaveDbHelper idSaveDbHelper;
	
	
	//定义一个方法 保存联系人信息
			public void saveContactInfo ( ContentResolver contentResolver , String[] info){
				
				idSaveDbHelper = IdSaveDbHelper.getDbHelper();
				values = new ContentValues();
				Uri rawContactUri = contentResolver.insert(RawContacts.CONTENT_URI,	values);
				rawContactID = ContentUris.parseId(rawContactUri);
				values.clear();
				// 1.保存联系人的姓名 ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
				values.put(StructuredName.GIVEN_NAME, info[0]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 3.保存联系人的昵称 ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[1]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 4.保存联系人的电话 ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[2]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 5.保存联系人的email ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[3]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 6.保存联系人的网址 ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[4]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 7.保存联系人的地址 ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE,"vnd.android.cursor.item/postal-address_v2");
				values.put(Data.DATA1, info[5]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 8.保存联系人的组织信息 ok
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[6]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 9.保存联系人的event
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[7]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				// 10.保存联系人的即时信息
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE,ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				values.put(Data.DATA1, info[8]);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
				//存储联系人的id
				idSaveDbHelper.getWritableDatabase().execSQL("insert into contacts_id values(null , ?)",new Object[] { rawContactID });
				idSaveDbHelper.close();
				
			}
			
			// 定义一个方法存储联系人到头像到数据库
			public void updateAvatar(ContentResolver contentResolver , Bitmap avatarBM){
				final ByteArrayOutputStream os = new ByteArrayOutputStream();
				avatarBM.compress(Bitmap.CompressFormat.PNG, 100, os); 
				byte[] avatar =os.toByteArray(); 
				Log.e("aa", rawContactID+"");
				values.put(Data.RAW_CONTACT_ID, rawContactID);
				values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
				values.put(Photo.PHOTO, avatar);
				contentResolver.insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				values.clear();
			}
			
			
			//读取数据库中联系人的id
			public void readContactId(){
				Cursor idCursor = idSaveDbHelper.getReadableDatabase().rawQuery("select * from contacts_id where contact_id > 0", null);
				int i = 0;
				StringBuilder sb = new StringBuilder();
				while(idCursor.moveToNext()){
				String id = idCursor.getString(idCursor.getColumnIndex("contact_id"));
				sb.append(id + "---");
				}
				String msg = sb.toString();
				Log.e("contactsid", msg);
			}

}
