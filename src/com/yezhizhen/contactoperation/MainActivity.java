package com.yezhizhen.contactoperation;

import java.util.Random;

import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private EditText nameEditText;
	private EditText nicknameEditText;
	private EditText phoneEditText;
	private EditText emailEditText;
	private EditText websiteEditText;
	private EditText adressEditText;
	private EditText orginfoEditText;
	private EditText eventEditText;
	private EditText timelyinfoEditText;
	private ImageView photoImageView;
	Bitmap avatarBM;
	long rawContactID;
	ContentValues values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		operateContact();

	}

	// 操作联系人的方法
	public void operateContact() {
		nameEditText = (EditText) findViewById(R.id.et_name);
		nicknameEditText = (EditText) findViewById(R.id.et_nickname);
		phoneEditText = (EditText) findViewById(R.id.et_phone);
		emailEditText = (EditText) findViewById(R.id.et_email);
		websiteEditText = (EditText) findViewById(R.id.et_website);
		adressEditText = (EditText) findViewById(R.id.et_adress);
		orginfoEditText = (EditText) findViewById(R.id.et_orginfo);
		eventEditText = (EditText) findViewById(R.id.et_event);
		timelyinfoEditText = (EditText) findViewById(R.id.et_timelyinfo);

		photoImageView = (ImageView) findViewById(R.id.iv_photo);
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
			values = new ContentValues();
			Uri rawContactUri = getContentResolver().insert(
					RawContacts.CONTENT_URI, values);
			rawContactID = ContentUris.parseId(rawContactUri);
			values.clear();

			// 2.保存联系人的 id 将联系人的contact_id存储到数据库
			IdSaveDbHelper idSaveDbHelper = IdSaveDbHelper
					.getHelper(MainActivity.this);
			idSaveDbHelper.saveContactId(rawContactID, idSaveDbHelper);

			String name = nameEditText.getText().toString().trim();
			String nickname = nicknameEditText.getText().toString().trim();
			String phone = phoneEditText.getText().toString().trim();
			String email = emailEditText.getText().toString().trim();
			String website = websiteEditText.getText().toString().trim();
			String adress = adressEditText.getText().toString().trim();
			String orginfo = orginfoEditText.getText().toString().trim();
			String event = eventEditText.getText().toString().trim();
			String timelyinfo = timelyinfoEditText.getText().toString().trim();
			String[] info = new String[] { name, nickname, phone, email,
					website, adress, orginfo, event, timelyinfo };
			idSaveDbHelper.saveContactInfo(getContentResolver(), rawContactID,
					values, info);
			// 保存联系人头像
			idSaveDbHelper.updateAvatar(rawContactID, values,
					getContentResolver(), avatarBM);
			Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_delete:
			// 删除联系人
			break;
		case R.id.btn_set_photo:
			// 设置头像
			Random photoName = new Random();
			int i = photoName.nextInt(10);
			avatarBM = BitmapFactory.decodeResource(getResources(),
					R.drawable.photo1 + i);
			photoImageView.setImageBitmap(avatarBM);
			break;
		default:
			break;
		}

	}

}
