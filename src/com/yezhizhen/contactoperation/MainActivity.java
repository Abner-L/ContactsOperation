package com.yezhizhen.contactoperation;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private EditText nameEditText;
	private EditText idEditText;
	private EditText nicknameEditText;
	private EditText phoneEditText;
	private EditText emailEditText;
	private EditText websiteEditText;
	private EditText adressEditText;
	private EditText orginfoEditText;
	private EditText eventEditText;
	private EditText timelyinfoEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		saveContact();

	}

	// 保存联系人的方法
	public void saveContact() {
		nameEditText = (EditText) findViewById(R.id.et_name);
		idEditText = (EditText) findViewById(R.id.et_contactid);
		nicknameEditText = (EditText) findViewById(R.id.et_nickname);
		phoneEditText = (EditText) findViewById(R.id.et_phone);
		emailEditText = (EditText) findViewById(R.id.et_email);
		websiteEditText = (EditText) findViewById(R.id.et_website);
		adressEditText = (EditText) findViewById(R.id.et_adress);
		orginfoEditText = (EditText) findViewById(R.id.et_orginfo);
		eventEditText = (EditText) findViewById(R.id.et_event);
		timelyinfoEditText = (EditText) findViewById(R.id.et_timelyinfo);

		ImageView photoImageView = (ImageView) findViewById(R.id.iv_photo);
		Button setPhotoButton = (Button) findViewById(R.id.btn_set_photo);
		Button deleteButton = (Button) findViewById(R.id.btn_delete);
		Button saveButton = (Button) findViewById(R.id.btn_save);

		saveButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this);
		setPhotoButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_save:
			// 保存联系人
			ContentValues values = new ContentValues();
		Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, values);
		long rawContactID = ContentUris.parseId(rawContactUri);
		values.clear();
			//1.保存联系人的姓名  ok
			String name = nameEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
			values.put(StructuredName.GIVEN_NAME, name);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//2.保存联系人的 id
			String id = idEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/identify");
			values.put(Data.DATA1, id);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//3.保存联系人的昵称  ok
			String nickname  = nicknameEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/nickname");
			values.put(Data.DATA1, nickname);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//4.保存联系人的电话 ok
			String phone = phoneEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
			values.put(Data.DATA1, phone);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//5.保存联系人的email ok
			String email = emailEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
			values.put(Data.DATA1, email);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//6.保存联系人的网址 ok
			String website = websiteEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/website");
			values.put(Data.DATA1, website);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//7.保存联系人的地址  ok
			String adress = adressEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/postal-address_v2");
			values.put(Data.DATA1, adress);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//8.保存联系人的组织信息 ok
			String orginfo = orginfoEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/organization");
			values.put(Data.DATA1, orginfo);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//9.保存联系人的event
			String event = eventEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, event);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
			//10.保存联系人的即时信息
			String timelyinfo = timelyinfoEditText.getText().toString().trim();
			values.put(Data.RAW_CONTACT_ID, rawContactID);
			values.put(Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
			values.put(Data.DATA1, timelyinfo);
			getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
			values.clear();
		Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_delete:
			// 删除联系人
			break;
		case R.id.btn_set_photo:
			// 设置头像

			break;
		default:
			break;
		}

	}

}
