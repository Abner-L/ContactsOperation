package com.yezhizhen.contactoperation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private ContactHelper contactHelper;
	private EditText contactNumberEditTexit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		operateContact();
	}

	// 操作联系人的方法
	public void operateContact() {
		
		Button createButton = (Button) findViewById(R.id.bt_create);
		Button deleteButton = (Button) findViewById(R.id.bt_delete);
		contactNumberEditTexit = (EditText) findViewById(R.id.et_create);
		createButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this);

		contactHelper = ContactHelper.getContactHelper(this);
		
				
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.bt_create:
			// 创建联系人String 
			String contactNumberString = contactNumberEditTexit.getText().toString().trim();
			Toast.makeText(this, contactNumberString, 1).show();
			int contactNumber = Integer.parseInt(contactNumberString);
			for (int i = 0; i < contactNumber; i++) {
				
				contactHelper.saveContactInfo();
			}
			
			Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.bt_delete:
			// 删除联系人
			contactHelper.deleteContactId();
			
			break;
			
		default:
			break;
		}
	}
}
