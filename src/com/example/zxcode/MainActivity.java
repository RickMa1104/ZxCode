package com.example.zxcode;

import com.dtr.zxing.activity.CaptureActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ImageView zx_img;
	EditText zx_url;
	Button zx_set, zx_get;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		zx_img = (ImageView) findViewById(R.id.zx_img);
		zx_url = (EditText) findViewById(R.id.zx_url);
		zx_get = (Button) findViewById(R.id.zx_get);
		zx_set = (Button) findViewById(R.id.zx_set);
		zx_set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				@SuppressWarnings("deprecation")
				int width = getWindowManager().getDefaultDisplay().getWidth();
				new ZxImg().loadZxing(zx_url.getText().toString(), MainActivity.this, zx_img, width);
			}
		});
		zx_get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 666);
                }
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 666 && resultCode == 667) {
			String url = data.getStringExtra("url");
			//拿到地址，执行判断处理方式
			Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
		}
	}
}
